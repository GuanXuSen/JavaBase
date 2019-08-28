package com.gx.spark.rdd.demo;

import com.gx.spark.comm.SparkUtil;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : JoinJavaApi
 * @Description :TOO 聚合
 * @Author : gx
 * @Date : 2019/8/26 17:25
 * @Version : 1.0
 */
public class JoinJavaApi {

    public static void main(String[] args) {

        // 创建JavaSparkContext
        JavaSparkContext sc = SparkUtil.getLocalJavaSparkContext();

        List<Integer> dataList = Arrays.asList(1,2,3,4,5,6,7);

        JavaRDD<Integer> javaRDD = sc.parallelize(dataList);


        JavaPairRDD<Integer,Integer> onePairRDD = javaRDD.mapToPair(
                (PairFunction<Integer,Integer,Integer>) pair ->
                        new Tuple2<>(pair, pair * pair)
        );

        JavaPairRDD<Integer,String> twoPairRDD = javaRDD.mapToPair(
                (PairFunction<Integer,Integer,String>) pair -> new Tuple2<>(pair, pair + "sss")
        );

        JavaPairRDD<Integer,Tuple2<Integer,String>> joinRest = onePairRDD.join(twoPairRDD);

        JavaRDD<String> resRDDList = joinRest.map(
                (Function<Tuple2<Integer, Tuple2<Integer, String>>, String>) integerTuple2Tuple2 -> {

                int key = integerTuple2Tuple2._1();

                int value1 = integerTuple2Tuple2._2()._1();

                String value2 = integerTuple2Tuple2._2()._2();

                return "(" + key + ",(" + value1 + "," + value2 + "))";
            }
        );


        List<String> res = resRDDList.collect();

        res.forEach(System.out::println);


    }

}
