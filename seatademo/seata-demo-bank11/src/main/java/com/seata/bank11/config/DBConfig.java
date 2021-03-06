package com.seata.bank11.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import  org.springframework.beans.factory.annotation.Qualifier;


/**
 * 作者：qiwj
 * 时间：2019/11/27
 */
@Configuration
//指定该SqlSession对象对应的dao(basePackages , dao扫包  sqlSessionFactoryRef: SqlSessionFactory对象注入到该变量中)
@MapperScan(basePackages = "com.seata.bank11.mapper.bank1",
        sqlSessionFactoryRef = "DB1Factory")

public class DBConfig {
    /**
     * 封装数据源对象创建, 该方法就已经将数据源的各个数据封装到该对象中
     *
     * @return
     */
    @Primary //必须要有, 说明该数据源是默认数据源
    @Bean(name = "bank1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")////读取的数据源前缀, 跟yml文件对应
    public DataSource DB1dataSource() {
        return new DruidDataSource();
    }
/*
    @Primary
    @Bean("dataSource1")
    public DataSourceProxy dataSource(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Primary
    @Bean(name = "DB1Factory")
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setTypeAliasesPackage("com.seata.bank11.pojo");
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
    //*/
    /**
     * SqlSession对象创建
     *
     * @param dataSource
     * @return
     * @throws Exception
     */

    @Primary
    @Bean(name = "DB1Factory")
    public SqlSessionFactory DB1Factory(@Qualifier("bank1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //这里注意, 设置该参数时打成jar启动会找不到该包下的类,目前未找到解决方案
        bean.setTypeAliasesPackage("com.seata.bank11.pojo");
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("classpath*:mapper/*.xml")//获取扫描xml文件
        );
        return bean.getObject();
    }



    /**
     * 事务的对象创建
     *
     * @param
     * @return
     * @throws Exception
     */

    @Primary
    @Bean(name = "transactionManager1")
    public PlatformTransactionManager txManagerUser() {
        return new DataSourceTransactionManager(DB1dataSource());
    }
//*/
}
//*/