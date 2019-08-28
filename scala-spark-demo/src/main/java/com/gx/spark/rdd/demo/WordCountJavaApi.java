package com.gx.spark.rdd.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : WordCountJavaApi
 * @Description :TOO 词频统计
 * @Author : gx
 * @Date : 2019/8/26 16:36
 * @Version : 1.0
 */
public class WordCountJavaApi {

    public static void main(String[] args) {
        // 创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("WordCountJavaApi")
                // 使用本地模式 "2147480000"
                .setMaster("local")
                .set("spark.testing.memory", "2147480000");

        // 创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 创建模拟数据
        List<String> lines = new ArrayList<String>();
        lines.add("你好 gx 你好 spark");
        lines.add("hello gx 你好 spark");
        lines.add("hello gx 你好 hadoop");
        lines.add("hello gx hello hadoop");
        lines.add("hello xg hello hadoop");

        // 序列化集合创建RDD
        JavaRDD<String> lineRDD = sc.parallelize(lines);


        // 切割单词，transform操作
        JavaRDD<String> wordRDD = lineRDD.flatMap((FlatMapFunction<String, String>) line -> Arrays.asList(line.split(" ")).iterator());


        // 映射为元组，transform操作
        JavaPairRDD<String, Integer> wordPairRDD = wordRDD.mapToPair(
                (PairFunction<String, String, Integer>) word -> new Tuple2<String, Integer>(word, 1));

        // 对key进行聚合，transform操作
        JavaPairRDD<String, Integer> word2CountRDD = wordPairRDD.reduceByKey(
                (org.apache.spark.api.java.function.Function2<Integer, Integer, Integer>)
                        (v1, v2) -> v1 + v2);

        // 将统计后的结果(word,count)转置为(count,word),transform操作
        JavaPairRDD<Integer, String> count2WordRDD = word2CountRDD.mapToPair(
                (PairFunction<Tuple2<String, Integer>, Integer, String>) tuple2 -> new Tuple2<Integer, String>(tuple2._2, tuple2._1));

        // 按照词频排序,参数false代表是降序排序，transform操作
        JavaPairRDD<Integer, String> count2WordSortedRDD = count2WordRDD.sortByKey(false);

        // 将排序后的元组再次转置，还原为原来的(word,count)形式，transform操作
        JavaPairRDD<String, Integer> word2CountSortedRDD = count2WordSortedRDD.mapToPair(
                (PairFunction<Tuple2<Integer, String>, String, Integer>) tuple2 -> new Tuple2<String, Integer>(tuple2._2, tuple2._1));

        // 将结果收集到一个集合里面，action操作
        List<Tuple2<String, Integer>> word2CountSortedList = word2CountSortedRDD.collect();

        // 遍历集合
        for (Tuple2<String, Integer> tuple2 : word2CountSortedList) {
            System.out.println(tuple2);
        }
    }
}
