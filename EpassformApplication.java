package com.chainsys.epassproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

public class EpassformApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpassformApplication.class, args);
		Logger logger = LoggerFactory.getLogger(EpassformApplication.class);

		logger.info("Welcome Epass");
	}

}

