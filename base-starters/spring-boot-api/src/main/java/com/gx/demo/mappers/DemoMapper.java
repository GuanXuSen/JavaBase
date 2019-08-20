package com.gx.demo.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName : DemoMapper
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/15 16:03
 * @Version : 1.0
 */
@Component
@Mapper
public interface DemoMapper {

    int select1();
}
