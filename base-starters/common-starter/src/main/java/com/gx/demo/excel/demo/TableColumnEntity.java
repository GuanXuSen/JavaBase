package com.gx.demo.excel.demo;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName : TableColumnEntity
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/29 18:26
 * @Version : 1.0
 */
@Data
@ToString
public class TableColumnEntity {

    private Long tableId;

    private String tableName;

    private String tableBizName;

    private String tableBizType;

    private String columnId;

    private String columnName;

    private String columnBizName;

}
