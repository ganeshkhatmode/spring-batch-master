package com.batch.user_processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class UserProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProcessingApplication.class, args);
	}

}
