package com.example.appreactspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppreactspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppreactspringApplication.class, args);
	}

}
