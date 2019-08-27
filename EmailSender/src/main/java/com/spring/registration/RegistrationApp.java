package com.spring.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RegistrationApp {
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApp.class, args);
	}
}
