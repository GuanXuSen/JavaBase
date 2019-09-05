package com.gx.demo.excel;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : ExcelDTO
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/4 9:18
 * @Version : 1.0
 */
public class ExcelDTO {

    private String fileName;

    private Map<String,List<String>>  sheets = Maps.newHashMap();

    private Map<String,List<List<String>>> datas = Maps.newHashMap();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, List<String>> getSheets() {
        return sheets;
    }

    public void setSheets(Map<String, List<String>> sheets) {
        this.sheets = sheets;
    }

    public Map<String, List<List<String>>> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, List<List<String>>> datas) {
        this.datas = datas;
    }
}
