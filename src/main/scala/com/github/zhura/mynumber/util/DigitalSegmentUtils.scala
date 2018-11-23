package com.github.zhura.mynumber.util

import com.github.zhura.mynumber.MagicNumbers
import org.slf4j.{Logger, LoggerFactory}

/**
  * Utils for working with digit strings
  */
object DigitalSegmentUtils {
  val logger: Logger = LoggerFactory.getLogger(getClass)
  val defaultMin: Int = MagicNumbers.wordLengthMinimum
  val defaultMax: Int = MagicNumbers.wordLengthMaximum
  val defaultRange: Range.Inclusive = MagicNumbers.wordLengthRange

  def toLong(s: String): Long = {
    try {
      s.toLong
    } catch {
      case _: NumberFormatException => -1L
    }
  }

  def toWord(number: String, buckets: Map[Int, Map[String, Seq[String]]]): Seq[String] = {
    val possibleWords = segmentsOfMinLengthOrLonger(number)
    val result = possibleWords.map { seq =>
      (seq, retrieveFromLib(seq, buckets))
    }.filter(tuple => tuple._2 != "")
    println((number, result))
    if(result.nonEmpty) {
      result.map(tuple => number.replace(tuple._1, tuple._2))
    } else {
      Vector(number)
    }
  }

  def segmentsOfMinLengthOrLonger(number: String, minLength: Int = defaultMin): Seq[String] = {
    assert(defaultRange.contains(minLength))
    assert(number.length >= minLength)
    val leftIndexRange = 0 to number.length - minLength
    leftIndexRange.flatMap { i =>
      val rightIndexRange = minLength + i to number.length
      rightIndexRange.map(c => number.substring(i, c))
    }.sortBy(s => s.length).reverse
  }

  private def retrieveFromLib(segment: String, buckets: Map[Int, Map[String, Seq[String]]]): String = {
    segment match {
      case s if s.length < defaultMin => ""
      case str => buckets.getOrElse(str.length, Map.empty).get(str) match {
        case None => ""
        case Some(s) => s.head // TODO use all, not only the first
      }
    }
  }
}
