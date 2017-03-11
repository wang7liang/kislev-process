package com.ws.kislev.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Created by wang7liang on 2017/3/11.
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig implements TransactionManagementConfigurer {
    @Autowired
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;

    @Autowired
    @Qualifier("readDataSource1")
    private DataSource readDataSource1;

    @Bean("writeDataTransactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(writeDataSource);
    }

    @Bean("readData1TransactionManager")
    public PlatformTransactionManager readData1TransactionManager(){
        return new DataSourceTransactionManager(readDataSource1);
    }

}
