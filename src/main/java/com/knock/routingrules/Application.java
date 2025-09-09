package com.knock.routingrules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@Slf4j
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Spring boot started ! :) ");
	}

}
