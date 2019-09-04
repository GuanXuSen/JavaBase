package com.gx.demo.excel;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : ExcelDTO
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/4 9:18
 * @Version : 1.0
 */
@Data
public class ExcelDTO {

    private String fileName;

    private Map<String,List<String>>  sheets = Maps.newHashMap();

    private Map<String,List<List<String>>> datas = Maps.newHashMap();
}
