package com.czy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/23 18:17
 * @description 事务管理配置
 */
@Configuration
@ComponentScan(basePackages = "com.czy.service")//扫描service层
@EnableTransactionManagement//开启事务支持
public class SpringTxConfig {

    @Bean
    public DataSourceTransactionManager getTransactionManager(DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
