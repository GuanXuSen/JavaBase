package com.gx.spark.rdd.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 词频统计
  */
object WordCountScalaApi {

  def main(args: Array[String]): Unit = {

    // 创建SparkConf
    val conf = new SparkConf()
      .setAppName("WordCountScalaApi")
      .setMaster("local")
      .set("spark.testing.memory", "2147480000")
    // 创建SparkContext
    val sc = new SparkContext(conf)
    // 创建模拟数据
    val lines = Array(
      "你好 gs 你好 spark",
      "hello gx 你好 spark",
      "hello gz 你好 hadoop",
      "hello gx hello hadoop",
      "hello zh hello hadoop"
    )
    // 序列化本地集合，创建RDD
    val lineRDD = sc.parallelize(lines)

    // 切割字符串，使其变成一个个的单词，transform操作
    val wordRDD = lineRDD.flatMap(line => line.split(" "))

    // 将单词映射为一个个的元组，transform操作
    val wordPairRDD = wordRDD.map(word => (word, 1))

    // 按照key进行聚合，统计每个单词的个数，transform操作
    val word2CountRDD = wordPairRDD.reduceByKey((v1, v2) => v1 + v2)

    // 对统计后的单词元组进行转置，变为(count,word)形式，transform操作
    val count2WordRDD = word2CountRDD.map(tuple2 => (tuple2._2, tuple2._1))

    // 对词频进行排序，false代表降序，transform操作
    val count2WordSortedRDD = count2WordRDD.sortByKey(false)

    // 对排序后的元组再次转置，transform操作
    val word2CountSortedRDD = count2WordSortedRDD.map(tuple2 => (tuple2._2, tuple2._1))

    // 将RDD的数据收集到一个Array中，action操作
    val word2CountSortedArray = word2CountSortedRDD.collect()

    // 遍历数组
    for (tuple2 <- word2CountSortedArray) {
        println(tuple2)
    }

  }
}
