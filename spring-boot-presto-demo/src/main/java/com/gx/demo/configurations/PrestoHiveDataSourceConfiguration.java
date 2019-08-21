package com.gx.demo.configurations;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @ClassName : PrestoHiveDataSourceConfiguration
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/15 16:54
 * @Version : 1.0
 */
public class PrestoHiveDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "")
    public DataSource hiveDatasource(){
        return DataSourceBuilder.create().build();
    }
}
