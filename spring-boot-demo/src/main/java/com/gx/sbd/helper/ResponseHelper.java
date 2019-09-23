package com.gx.sbd.helper;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @ClassName : ResponseHelper
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/20 10:22
 * @Version : 1.0
 */
public class ResponseHelper {

    /**
     * 输出 json
     * @param response
     * @param msg
     */
    public static void wirterJosn(HttpServletResponse response,String msg){
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.println(msg);
        }catch (Exception e){

        }finally {
            if(Objects.nonNull(printWriter)){
                printWriter.close();
            }
        }
    }
}
