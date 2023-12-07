package com.forum.mechanism.connector;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 資料庫的連線設定
 * 
 * @author ricky_wang
 */
@Configuration
@MapperScan(basePackages = "com.forum.api.service.mybatis.mapper.customerize", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConnector {

	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "datasource")
	@ConfigurationProperties("spring.datasource.hikari")
	public DataSource dataSource() {
		return dataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "transaction")
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(this.dataSource());
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory createSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

}
