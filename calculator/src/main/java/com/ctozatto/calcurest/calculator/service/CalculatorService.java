package com.ctozatto.calcurest.calculator.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(CalculatorServiceProperties.class)
public class CalculatorService {
	
	private final CalculatorServiceProperties properties;
	
	public CalculatorService(CalculatorServiceProperties properties) {
		this.properties = properties;
	}
	
	public String message() {
		return this.properties.getMessage();
	}

}
