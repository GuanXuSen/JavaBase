package com.gx.sbd.excel.demo;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName : Demo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/30 14:38
 * @Version : 1.0
 */
public class Demo {


    public static void main(String[] args) {
//        String filePath_xlsx = "E:\\gxtest.xlsx";
//        String filePath_xls = "E:\\gxtest.xls";
//
//
//        File xlsFile = new File(filePath_xlsx);
//
//       // File xlxsFile = new File(filePath_xlsx);
//
//
//        System.out.println(xlsFile.getName());
//
//        String name1 = xlsFile.getName();
//
//        Boolean isXls = name1.endsWith(ExcelTypeEnum.XLS.getName());
//
//        Boolean isXlsx = name1.endsWith(ExcelTypeEnum.XLSX.getName());
//
//        System.out.println(isXls + " ** " + isXlsx);

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


}
