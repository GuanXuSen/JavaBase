package com.gx.demo.utils;




public enum ResultCodeEnum {

    SUCCESS("0000","成功"),
    FAIL("0001","失败");

    private String code;

    private String message;


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    ResultCodeEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 通过 code 获取 枚举
     * @param code
     * @return
     */
    public static  ResultCodeEnum getEnumByCode(String code){

        for(ResultCodeEnum _enum : values()){

            if(_enum.getCode().equals(code)){
                return _enum;
            }
        }
        return null;
    }

}
