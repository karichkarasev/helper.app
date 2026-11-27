package com.sandwich.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sandwich.helper")
@EnableAutoConfiguration
public class HelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelperApplication.class, args);
	}

}
