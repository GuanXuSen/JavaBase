package com.gx.sbd.servers;

import com.gx.demo.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName : RedisDemo
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/16 15:48
 * @Version : 1.0
 */
@Component
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Object test1() {
        BaseResponse response = BaseResponse.newInstance();
        try{
            redisTemplate.opsForValue().set("zhangsan","张三");
            response.success();
        }catch (Exception e){
            logger.error("失败",e);
        }
        return response;
    }
}
