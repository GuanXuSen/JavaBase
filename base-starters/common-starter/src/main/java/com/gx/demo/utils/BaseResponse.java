package com.gx.demo.utils;


import com.google.common.collect.Maps;
import com.gx.demo.CommonBaseConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;


public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -1;

    private String code;

    private String message;

    private Map<String,Object> dataMap;



    BaseResponse(String code,String message,Map<String,Object> dataMap){
       this.code = code;
       this.message = message;
       this.dataMap = dataMap;
    }

    BaseResponse(){
        this(ResultCodeEnum.FAIL.getCode(),ResultCodeEnum.FAIL.getMessage(), Maps.newHashMap());
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public  Map<String,Object> toResponseMap(){
        Map<String,Object> result = Maps.newHashMap();
        result.put("code",code);
        result.put("message",message);
        result.put("dateMap",dataMap);
        return result;
    }

    public BaseResponse success(String message){
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = message;
        return this;
    }


    public BaseResponse success(){
        return this.success(ResultCodeEnum.SUCCESS.getMessage());
    }

    public BaseResponse fail(String message){
        this.code = ResultCodeEnum.FAIL.getCode();
        this.message = message;
        return this;
    }

    public BaseResponse fail403(){
        this.code = CommonBaseConstants.CODE_403;
        this.message = CommonBaseConstants.CODE_403_NAME;
        return this;
    }
    public BaseResponse fail404(){
        this.code = CommonBaseConstants.CODE_404;
        this.message = CommonBaseConstants.CODE_404_NAME;
        return this;
    }
    public BaseResponse fail405(){
        this.code = CommonBaseConstants.CODE_405;
        this.message = CommonBaseConstants.CODE_405_NAME;
        return this;
    }
    public BaseResponse fail500(){
        this.code = CommonBaseConstants.CODE_500;
        this.message = CommonBaseConstants.CODE_500_NAME;
        return this;
    }

    public BaseResponse fail403(String message){
        this.code = CommonBaseConstants.CODE_403;
        this.message = CommonBaseConstants.CODE_403_NAME;
        if(StringUtils.isNotBlank(message)){
            this.message = message;
        }
        return this;
    }
    public BaseResponse fail404(String message){
        this.code = CommonBaseConstants.CODE_404;
        this.message = CommonBaseConstants.CODE_404_NAME;
        if(StringUtils.isNotBlank(message)){
            this.message = message;
        }
        return this;
    }
    public BaseResponse fail405(String message){
        this.code = CommonBaseConstants.CODE_405;
        this.message = CommonBaseConstants.CODE_405_NAME;
        if(StringUtils.isNotBlank(message)){
            this.message = message;
        }
        return this;
    }
    public BaseResponse fail500(String message){
        this.code = CommonBaseConstants.CODE_500;
        this.message = CommonBaseConstants.CODE_500_NAME;
        if(StringUtils.isNotBlank(message)){
            this.message = message;
        }
        return this;
    }


    public BaseResponse fail(){
        return fail(ResultCodeEnum.FAIL.getMessage());
    }

    public BaseResponse put(String key,Object value){
        this.dataMap.put(key,value);
        return this;
    }

    public static BaseResponse newInstance(){
        return new BaseResponse();
    }

}
