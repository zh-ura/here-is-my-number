package com.github.zhura.mynumber.util

import com.github.zhura.mynumber.util.spark.WordBucketingUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest._

class DigitalSegmentUtilsTest extends FunSuite {

  test("toLong should parse string") {
    assert(DigitalSegmentUtils.toLong("123") === 123L)
  }

  test("toLong should be -1 for non-parseable string") {
    assert(DigitalSegmentUtils.toLong("ab") === -1L)
  }

  test("segmentsOfMinLengthOrLonger for 1234 returns 1234") {
    val result = DigitalSegmentUtils.segmentsOfMinLengthOrLonger("1234")
    assert(result.length === 1)
    assert(result.contains("1234"))
  }

  test("segmentsOfMinLengthOrLonger for 12345 returns 1234, 12345, 2345") {
    val result = DigitalSegmentUtils.segmentsOfMinLengthOrLonger("12345")
    assert(result.length === 3)
    Vector("1234", "12345", "2345").foreach(el =>
      assert(result.contains(el))
    )
  }
/*
  test("toWord 8005678923 contains 800lost923") {
    val conf = new SparkConf().setAppName("words-app").setMaster("local")
    val sc = new SparkContext(conf)
    val buckets = WordBucketingUtils.bucketWords("words", sc)
    val lengthBuckets = DigitalSegmentUtils.defaultRange.map(length => (length, WordBucketingUtils.bucketsOfLength(length, buckets))).toMap
    sc.stop()
    val result = DigitalSegmentUtils.toWord("8005678923", lengthBuckets)
    println(result)
    assert(result.contains("800lost923"))
  }
*/

}