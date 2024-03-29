package com.gx.sbd.demo;

import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName : CollectionDemo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/17 10:50
 * @Version : 1.0
 */
public class CollectionDemo {

    /**
     * collections 测试
     */
    public static void test1(){
        List<String> aList = new ArrayList<String>();
        aList.add("aaa");
        aList.add("bbb");
        aList.add("ccc");
        List<String> bList = new ArrayList<String>();
        bList.add("aaa");
        bList.add("ddd");
        bList.add("eee");
        // 并集
        Collection<String> unionList = CollectionUtils.union(aList, bList);
        // 交集
        Collection<String> intersectionList = CollectionUtils.intersection(aList, bList);
        // 是否存在交集
        boolean isContained = CollectionUtils.containsAny(aList, bList);
        // 交集的补集
        Collection<String> disjunctionList = CollectionUtils.disjunction(aList, bList);
        // 集合相减
        Collection<String> subtractList = CollectionUtils.subtract(aList, bList);

        // 排序
        Collections.sort((List<String>) unionList);
        Collections.sort((List<String>) intersectionList);
        Collections.sort((List<String>) disjunctionList);
        Collections.sort((List<String>) subtractList);

        // 测试
        System.out.println("A: " + ArrayUtils.toString(aList.toArray()));
        System.out.println("B: " + ArrayUtils.toString(bList.toArray()));
        System.out.println("A has one of B? : " + isContained);
        System.out.println("Union(A, B): "
                + ArrayUtils.toString(unionList.toArray()));
        System.out.println("Intersection(A, B): "
                + ArrayUtils.toString(intersectionList.toArray()));
        System.out.println("Disjunction(A, B): "
                + ArrayUtils.toString(disjunctionList.toArray()));
        System.out.println("Subtract(A, B): "
                + ArrayUtils.toString(subtractList.toArray()));

    }


    /**
     * list 拆分
     */
    public static void test(){
        List<String> aList = Lists.newLinkedList();
        aList.add("aaa");
        aList.add("bbb");
        aList.add("ccc");
        List<List<String>> res = Lists.partition(aList,1);

        res.forEach(System.out::println);

    }


    public static void PariTest(){

        Pair<String, String> pair = new Pair<>("aku", "female");
        String key = pair.getKey();
        String value = pair.getValue();

        System.out.println("输出："+key);
        System.out.println("值："+value);

    }


    public static void main(String[] args) {
        PariTest();
    }

}

