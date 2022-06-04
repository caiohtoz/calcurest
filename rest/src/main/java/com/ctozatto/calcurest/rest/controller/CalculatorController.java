package com.ctozatto.calcurest.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctozatto.calcurest.calculator.service.CalculatorService;

@RestController
public class CalculatorController {

	private final CalculatorService calcService;
	
	public CalculatorController(CalculatorService calcService) {
		this.calcService = calcService;
	}
	
	@GetMapping("/")
	public String home() {
		return calcService.message();
	}
	
}
