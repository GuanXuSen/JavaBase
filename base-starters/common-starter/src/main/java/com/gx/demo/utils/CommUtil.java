package com.gx.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName : CommUtil
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/7/23 10:59
 * @Version : 1.0
 */
public class CommUtil {

        public static String getUUID(){
            return UUID.randomUUID().toString().replace("-","");
        }


    /**
     * list 拆分
     * @param list 要拆分的 list
     * @param n 拆分个数
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> list, int n){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=list.size()%n;  //(先计算出余数)
        int number=list.size()/n;  //然后是商
        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=list.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=list.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

    /** list 拆分
     * @param list
     * @param len
     * @return
     */
    public static List<List<String>> splitList(List<String> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<String>> result = new ArrayList<List<String>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<String> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

}
