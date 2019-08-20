package com.gx.demo.service.imp;

import com.gx.demo.mappers.DemoMapper;
import com.gx.demo.service.PrestoDemoService;
import com.gx.demo.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName : PerstoDemoServiceImpl
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/15 11:00
 * @Version : 1.0
 */
@Component
public class PrestoDemoServiceImpl implements PrestoDemoService {

    @Autowired
    private DemoMapper mapper;


    @Autowired
    @Qualifier("prestoJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object test1() {
        BaseResponse response = BaseResponse.newInstance();
        response.success().put("data",jdbcTemplate.queryForList("show tables"));
        return response.toResponseMap();
    }

    @Override
    public Object select1() {
        BaseResponse response = BaseResponse.newInstance();
        response.success().put("data",mapper.select1());
        return response.toResponseMap();
    }


}
