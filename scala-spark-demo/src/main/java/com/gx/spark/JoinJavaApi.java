package com.gx.spark;

import com.gx.spark.comm.SparkUtil;
import org.apache.spark.api.java.JavaSparkContext;

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


    }
}
