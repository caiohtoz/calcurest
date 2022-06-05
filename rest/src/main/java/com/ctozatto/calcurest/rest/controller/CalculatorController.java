package com.ctozatto.calcurest.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctozatto.calcurest.calculator.domain.BasicOperation;
import com.ctozatto.calcurest.calculator.service.CalculatorService;
import com.ctozatto.calcurest.rest.message.RabbitMQSender;

@RestController
@RequestMapping("/calcurest/")
public class CalculatorController {

	private final CalculatorService calcService;
	private RabbitMQSender messageSender;
	
	@Value("${rest.sendSuccess}")
	private String sendSuccess;
	
	@Value("${rest.sendFailure}")
	private String sendFailure;
	
	@Autowired
	public CalculatorController(CalculatorService calcService, RabbitMQSender messageSender) {
		this.calcService = calcService;
		this.messageSender = messageSender;
	}
	
	@GetMapping("home")
	public String home() {
		return calcService.homeMessage();
	}
	
	@PostMapping(value = "calculate")
	public String publishUserDetails(@RequestBody BasicOperation operation) {
		try {
			messageSender.send(operation);
		} catch(Exception e) {
			return sendFailure + e.getMessage();
		}
		return sendSuccess;
	}

	public RabbitMQSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(RabbitMQSender messageSender) {
		this.messageSender = messageSender;
	}
	
}
