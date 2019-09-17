package com.gx.bdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : AppRun
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/17 15:28
 * @Version : 1.0
 */
@SpringBootApplication
public class AppRun {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppRun.class);
        application.run(args);
    }
}
