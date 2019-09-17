package com.gx.sbd.controllers;

import com.gx.sbd.servers.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : RedisController
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/16 15:49
 * @Version : 1.0
 */
@RestController
@RequestMapping(value = "redis")
public class RedisController {

    @Autowired
    private RedisService redisService;


    @GetMapping(value = "test1")
    public Object test1(){
        return redisService.test1();
    }
}
