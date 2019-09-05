package com.gx.sbd;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName : ApplicationRun
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/20 14:07
 * @Version : 1.0
 */
@SpringBootApplication
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationRun.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
