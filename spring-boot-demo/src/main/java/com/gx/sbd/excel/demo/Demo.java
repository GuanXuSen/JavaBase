package com.gx.sbd.excel.demo;


import com.google.common.collect.Lists;
import com.gx.sbd.excel.ExcelUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : Demo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/30 14:38
 * @Version : 1.0
 */
public class Demo {

    private static void test1(){
        List<String> allList = Lists.newArrayList();
        for(int i = 1; i<= 1000;i++){
            allList.add("数据: "+ i);
        }

        // List<List<String>> lists = averageAssign(allList,100);

        //  List<List<String>> lists2 = splitList(allList,100);
        // lists.forEach(System.out::println);
        //  lists2.forEach(System.out::println);

        AtomicInteger integer = new AtomicInteger(0);
        allList.forEach(name ->{
            integer.getAndIncrement();
            if(integer.get() == 100){
                System.out.println(name);
                integer.set(0);
            }

        });
    }

    public static void main(String[] args) {
        String filePath_xlsx = "E:\\gxtest.xlsx";
        String filePath_xls = "E:\\gxtest.xls";


        File xlsFile = new File(filePath_xlsx);

       // File xlxsFile = new File(filePath_xlsx);

    Map<String,List<List<String>>> map = ExcelUtil.createExcelReader()
            .setFile(xlsFile)
            .skipFirstLine()
            .read();

        Set<String> sheetNames = map.keySet();

        sheetNames.forEach(System.out::println);



    }


}
