package com.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AtResourcesApi2Application {

	public static final Logger logger = LogManager.getLogger(AtResourcesApi2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(AtResourcesApi2Application.class, args);
	}

}
