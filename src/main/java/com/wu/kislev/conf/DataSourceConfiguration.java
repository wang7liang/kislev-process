package com.wu.kislev.conf;

import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by macmini-g1hw on 17/3/8.
 */
@Configuration
public class DataSourceConfiguration {

    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource(){
        //return DataSourceBuilder.create().type(dataSourceType).build();
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.read1")
    public DataSource readDataSource1(){
        //return DataSourceBuilder.create().type(dataSourceType).build();
        return DataSourceBuilder.create().build();
    }


}

