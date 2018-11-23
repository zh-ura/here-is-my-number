package com.github.zhura.mynumber.util.spark

import com.github.zhura.mynumber.MagicNumbers
import com.github.zhura.mynumber.util.WordUtils
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Holds words in memory in structured order:
  * - Buckets by digital representation
  */
object WordBucketingUtils {

  /**
    * Word Processing on startup utility
    * Put words into memory buckets
    * Rules:
    * words of length of 4 (non-small human memory unit) to 10 (phone max length)
    * each bucket is a phone keyboard-based numeric representation
    * @param wordsRaw mac vocabulary
    * @param spark spark context
    * @return spark RDD: key - numeric representation; value - set of words
    */
  def bucketWords(wordsRaw: String, spark: SparkContext): RDD[(String, Iterable[String])] = {
    spark.textFile(wordsRaw)
    .filter(w => MagicNumbers.wordLengthRange.contains(w.length))
    .map(word => word.toLowerCase)
    .distinct()
    .groupBy(word => WordUtils.wordToDigital(word))
    .map{case (group, wordIterable) => (group, wordIterable.toVector)}
  }

  def bucketsOfLength(length: Int, buckets: RDD[(String, Iterable[String])]): Map[String, Seq[String]] = {
    val filtered = buckets.filter{case (digital, _) => digital.length == length}
    filtered.collect().map{case (digital, iterable) => (digital, iterable.toVector)}.toMap
  }
}
