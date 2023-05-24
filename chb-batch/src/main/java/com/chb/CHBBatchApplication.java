package com.chb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "com.chb", "com.chb.batch" })
//@EnableDiscoveryClient
public class CHBBatchApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CHBBatchApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CHBBatchApplication.class, args);
	}
}
