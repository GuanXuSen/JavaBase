package com.gx.sbd.threads;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName : ThreadConfig
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/16 11:27
 * @Version : 1.0
 */
@Configuration
public class ThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        int corePoolSize = 5;
        int maximumPoolSize = 20;
        long keepAliveTime = 1000;
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
