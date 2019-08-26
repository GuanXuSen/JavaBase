package com.gx.spark.comm;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * @ClassName : SparkUtil
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/26 17:34
 * @Version : 1.0
 */
public class SparkUtil {

    /**
     *
     * @return
     */
    public static SparkConf buildLocalSparkConf(){
        return buildLocalSparkConf("default");
    }

    /**
     * 本地sparkconf
     * @param className
     * @return
     */
    public static SparkConf buildLocalSparkConf(String className){
      return new SparkConf()
                .setAppName(className)
                .setMaster("local")
                .set("spark.testing.memory", "2147480000");
    }

    public static JavaSparkContext getLocalJavaSparkContext(){
        return getJavaSparkContext(buildLocalSparkConf());
    }


    public static SparkConf buildSparkConf(){
        return new SparkConf();
    }

    public static JavaSparkContext getJavaSparkContext(SparkConf sparkConf){
        return new JavaSparkContext(sparkConf);
    }




}
