package com.gx.sbd.configs;

import com.gx.sbd.interceptors.BaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName : AppMvcConfig
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/23 11:26
 * @Version : 1.0
 */
@Configuration
public class AppMvcConfig  extends WebMvcConfigurerAdapter {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor)
            .addPathPatterns("/**");
        super.addInterceptors(registry);

    }
}
