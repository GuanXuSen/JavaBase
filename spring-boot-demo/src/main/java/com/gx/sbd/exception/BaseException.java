package com.gx.sbd.exception;

import com.gx.demo.utils.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName : BaseException
 * @Description :TOO 自定义异常
 * @Author : gx
 * @Date : 2019/9/10 14:29
 * @Version : 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Exception exception;
    private BaseResponse baseResponse;

    public BaseException(Exception exception, BaseResponse baseResponse) {
        this.exception = exception;
        this.baseResponse = baseResponse;
    }

    public BaseException(String errMsg) {
        this.getErr(errMsg);
    }

    public BaseResponse getErr(String errMsg){
        return BaseResponse.newInstance().fail(errMsg);
    }
}
