package com.gx.demo.excel.demo;

import com.google.common.collect.Lists;
import com.gx.demo.utils.CommUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : ExclTest
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/29 18:25
 * @Version : 1.0
 */
public class ExclTest {


    public static List<TableColumnEntity> getList(){

        List<TableColumnEntity> list = Lists.newArrayList();

        for (int i = 0; i < 100; i++) {
            TableColumnEntity entity = new TableColumnEntity();
            entity.setTableId((long) (i/10));
            entity.setTableName(i/10+"tabNa");
            entity.setColumnId(CommUtil.getUUID());
            entity.setColumnName("字段"+i);
            list.add(entity);
        }


        return list;

    }

    public static void main(String[] args) {

        List<TableColumnEntity> tableColumns = getList();


        //tableColumns.forEach(System.out::println);

        Map<Long,List<TableColumnEntity>> map = tableColumns.stream()
                .collect(Collectors.toMap(
                        TableColumnEntity::getTableId,
                        cols -> Lists.newArrayList(cols),
                        ( List<TableColumnEntity> k1, List<TableColumnEntity> k2)->{
                             k2.addAll(k1);
                            return k2;
                        }
                ));

        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });



    }
}
