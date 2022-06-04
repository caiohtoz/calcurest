package com.ctozatto.calcurest.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ctozatto.calcurest")
public class CalcurestRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalcurestRestApplication.class, args);
	}

}
