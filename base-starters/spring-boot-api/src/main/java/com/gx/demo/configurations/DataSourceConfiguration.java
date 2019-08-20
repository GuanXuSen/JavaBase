package com.gx.demo.configurations;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @ClassName : DataSourceConfiguration
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/8/15 10:47
 * @Version : 1.0
 */
@Configuration
@MapperScan(basePackages = "com.gx.demo.mappers", sqlSessionTemplateRef  = "prestoSqlSessionTemplate")
public class DataSourceConfiguration {

    /**
     * presto datasource
     * @return
     */
    @Bean(name = "prestoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource prestoDataSource(){
        return  DataSourceBuilder.create().build();
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDriverClassName("com.facebook.presto.jdbc.PrestoDriver");
//        hikariDataSource.setJdbcUrl("jdbc:presto://192.168.155.228:8080/mysql/base_demo");
//        hikariDataSource.setMaximumPoolSize(10);
//        hikariDataSource.setMinimumIdle(5);
//        hikariDataSource.setIdleTimeout(10000);
//        hikariDataSource.setConnectionTimeout(60000);
//        hikariDataSource.setUsername("mysql");
//        hikariDataSource.setPassword("xxxx");
//        hikariDataSource.setAutoCommit(false);
//        return hikariDataSource;
    }

    /**
     * pesto jdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean(name = "prestoJdbcTemplate")
    public JdbcTemplate prestoJdbcTemplate(@Qualifier("prestoDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "prestoSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("prestoDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /*加载mybatis全局配置文件*/
        //bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        /*加载所有的mapper.xml映射文件*/
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "prestoTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("prestoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "prestoSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("prestoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
