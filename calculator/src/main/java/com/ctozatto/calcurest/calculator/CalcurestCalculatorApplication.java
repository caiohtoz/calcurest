package com.ctozatto.calcurest.calculator;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class CalcurestCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalcurestCalculatorApplication.class, args);
	}

}
