package com.ctozatto.calcurest.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctozatto.calcurest.calculator.domain.OperatorEnum;
import com.ctozatto.calcurest.rest.message.RabbitMQSender;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/calcurest/")
public class CalculatorController {

	private RabbitMQSender messageSender;
	
	@Value("${rest.sendSuccess}")
	private String sendSuccess;
	
	@Value("${rest.sendFailure}")
	private String sendFailure;
	
	@Value("${rest.operationFailure}")
	private String operationFailure;
	
	@Autowired
	public CalculatorController(RabbitMQSender messageSender) {
		this.messageSender = messageSender;
	}
	
	@GetMapping(value = "add")
	public String addition(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.ADDITION;
		JsonObject result = processRequest(req, operator);

		return result.toString();
	
	}
	
	@GetMapping(value = "subtract")
	public String subtraction(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.SUBTRACTION;
		JsonObject result = processRequest(req, operator);
		
		return result.toString();
	
	}
	
	@GetMapping(value = "multiply")
	public String multiplication(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.MULTIPLICATION;
		JsonObject result = processRequest(req, operator);
		
		return result.toString();
	
	}
	
	@GetMapping(value = "divide")
	public String division(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.DIVISION;
		JsonObject result = processRequest(req, operator);
			
		return result.toString();
	
	}
		
	public JsonObject processRequest(HttpServletRequest req, OperatorEnum operator)	{
		
		JsonObject result = new JsonObject();
		String paramA = null;
		String paramB = null;
		
		if (req != null) {
			paramA = req.getParameter("a");
			paramB = req.getParameter("b");
		}
		
		if (paramA != null && paramB != null) {
			try {
				result = messageSender.sendAndReceive(paramA, paramB, operator);
			} catch(Exception e) {
				result.addProperty("error", operationFailure + e.getMessage());
				return result;
			}
		} else {
			result.addProperty("error", operationFailure + "Request missing operands.");
		}
		
		if (result != null)
			return result;
		
		result = new JsonObject();
		result.addProperty("error", operationFailure + "Result returned with a null value.");
		return result;
	}

	public RabbitMQSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(RabbitMQSender messageSender) {
		this.messageSender = messageSender;
	}
	
}
