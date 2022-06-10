package com.eProfile.eProfileExperts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.eProfile.eProfileExperts.controller")
public class EProfileExpertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EProfileExpertsApplication.class, args);
	}

}
