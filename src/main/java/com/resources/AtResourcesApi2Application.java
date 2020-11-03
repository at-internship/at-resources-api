package com.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class AtResourcesApi2Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(AtResourcesApi2Application.class, args);
	}
}
