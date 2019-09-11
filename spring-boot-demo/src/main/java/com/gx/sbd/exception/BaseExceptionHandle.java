package com.gx.sbd.exception;

import com.gx.demo.utils.BaseResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    /**
     * 请求路径无法找到异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse notFoundException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        return BaseResponse.newInstance().fail404();
    }

    /**
     * 请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse httpRequestMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        return BaseResponse.newInstance().fail405();
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public BaseResponse parameterException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        return BaseResponse.newInstance().fail403();
    }

    /**
     * 上传文件过大异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseResponse maxUploadSizeExceededException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        return BaseResponse.newInstance().fail403("上传文件过大！！！");
    }



}
