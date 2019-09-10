package com.gx.sbd.exception;

import com.gx.demo.utils.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : BaseExceptionHandle
 * @Description :TOO 处理controller 层未捕捉的异常
 * @Author : gx
 * @Date : 2019/9/10 14:01
 * @Version : 1.0
 */
@RestControllerAdvice
public class BaseExceptionHandle {

    /**
     * controller 层 全局异常 捕获处理
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse errResponse(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        return BaseResponse.newInstance().fail("系统异常，请联系管理员！！！");
    }
}
