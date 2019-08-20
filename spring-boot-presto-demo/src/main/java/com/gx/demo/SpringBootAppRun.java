package com.gx.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author gx
 */
@SpringBootApplication
public class SpringBootAppRun {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootAppRun.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
