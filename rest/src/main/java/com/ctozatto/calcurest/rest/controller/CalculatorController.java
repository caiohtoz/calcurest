package com.ctozatto.calcurest.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctozatto.calcurest.calculator.domain.OperatorEnum;
import com.ctozatto.calcurest.rest.domain.OperationResult;
import com.ctozatto.calcurest.rest.message.RabbitMQSender;

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
	public OperationResult addition(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.ADDITION;
		OperationResult result = processRequest(req, operator);
		
		return result;
	
	}
	
	@GetMapping(value = "subtract")
	public OperationResult subtraction(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.SUBTRACTION;
		OperationResult result = processRequest(req, operator);
		
		return result;
	
	}
	
	@GetMapping(value = "multiply")
	public OperationResult multiplication(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.MULTIPLICATION;
		OperationResult result = processRequest(req, operator);
		
		return result;
	
	}
	
	@GetMapping(value = "divide")
	public OperationResult division(HttpServletRequest req) {
		
		OperatorEnum operator = OperatorEnum.DIVISION;
		OperationResult result = processRequest(req, operator);
			
		return result;
	
	}
		
	public OperationResult processRequest(HttpServletRequest req, OperatorEnum operator)	{
		
		OperationResult received = new OperationResult();
		String paramA = null;
		String paramB = null;
		
		if (req != null) {
			paramA = req.getParameter("a");
			paramB = req.getParameter("b");
		}
		
		if (paramA != null && paramB != null) {
			try {
				received = messageSender.sendAndReceive(paramA, paramB, operator);
			} catch(Exception e) {
				received.setResult("");
				received.setError(operationFailure + e.getMessage());
				return received;
			}
		} else {
			received.setResult("");
			received.setError(operationFailure + "Request is missing operands.");
		}
		
		if (received != null)
			return received;
		
		received = new OperationResult();
		received.setResult("");
		received.setError(operationFailure + "Result returned with a null value.");
		return received;
	}

	public RabbitMQSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(RabbitMQSender messageSender) {
		this.messageSender = messageSender;
	}
	
}
