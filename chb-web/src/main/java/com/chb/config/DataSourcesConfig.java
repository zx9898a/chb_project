package com.chb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourcesConfig {

	@Autowired
	AppcationPropertiesConfig appcationPropertiesConfiguration;

	@Bean(name = "config1")
	@Primary
	@ConfigurationProperties("spring.datasource.ds1")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	// for local hikari pool use
	@Bean(name = "ds1")
	@Primary
	@ConfigurationProperties("spring.datasource.ds1")
	public DataSource firstDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setUsername(appcationPropertiesConfiguration.getDataSourceUserName());
		dataSource.setPassword(appcationPropertiesConfiguration.getDataSourcePassword());
		dataSource.setJdbcUrl(appcationPropertiesConfiguration.getDataSourceJdbcUrl());
		dataSource.setDriverClassName(appcationPropertiesConfiguration.getDataSourceDriverClassName());
		// dataSource.setPoolName("chb-esb-camel-hikaricp");
		dataSource.setPoolName("chb-hikaricp");
		dataSource.setMaximumPoolSize(5);
		dataSource.setMinimumIdle(5);
		dataSource.setConnectionTimeout(3000);
		dataSource.setIdleTimeout(10000);
		dataSource.setMaxLifetime(30000);
		return dataSource;
	}

}
