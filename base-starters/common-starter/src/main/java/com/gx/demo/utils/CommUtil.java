package com.gx.demo.utils;

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
}
