package com.chb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource(value="classpath:application.properties",encoding = "utf-8")
@Data
public class AppcationPropertiesConfig {
	
	@Value("${sba.datasource.driver-class-name}")
	private String dataSourceDriverClassName;
	@Value("${sba.dataSource.username}")
	private String dataSourceUserName;
	@Value("${sba.dataSource.password}")
    private String dataSourcePassword;
    @Value("${sba.dataSource.jdbcUrl}")
    private String dataSourceJdbcUrl;
    
//  @Bean(name = "NettyControl")
//	public NettyControl eachNettyControl() throws InterruptedException {
//		return new NettyControl("127.0.0.1", 10009, 11009);
//	}
//  @Bean
//  public NettyStart NettyStart(){
//      return new NettyStart();
//  }
    
}
