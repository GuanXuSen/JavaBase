package com.gx.demo.excel;

/**
 * @ClassName : ExcelTypeEnum
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/30 14:19
 * @Version : 1.0
 */
public enum ExcelTypeEnum {

    XLS(0,".xls"),
    XLSX(1,".xlsx"),
    OTHER(-1,"other");

    private Integer code;

    private String name;

    ExcelTypeEnum(Integer code ,String name ){
        this.code = code;
        this.name = name;
    }

    public Integer getCode(){return this.code;}
    public String getName(){return this.name;}


    public static ExcelTypeEnum getByCode(Integer code){
         ExcelTypeEnum excelTypeEnum = ExcelTypeEnum.OTHER;
         for(ExcelTypeEnum typeEnum : ExcelTypeEnum.values()){
             if(code == typeEnum.getCode()){
                 excelTypeEnum = typeEnum;
             }
         }
         return excelTypeEnum;
    }

    public static ExcelTypeEnum getByName(String name){
        ExcelTypeEnum excelTypeEnum = ExcelTypeEnum.OTHER;
        for(ExcelTypeEnum typeEnum : ExcelTypeEnum.values()){
            if(typeEnum.getName().equals(name)){
                excelTypeEnum = typeEnum;
            }
        }
        return excelTypeEnum;
    }

    public static ExcelTypeEnum getByFileName(String fileName){
        ExcelTypeEnum typeEnum = null;
        for (ExcelTypeEnum type : ExcelTypeEnum.values()){
            if(fileName.endsWith(type.getName())){
                typeEnum = type;
            }
        }
        return typeEnum;
    }

}
