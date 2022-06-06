package com.ctozatto.calcurest.rest.message;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ctozatto.calcurest.calculator.domain.BasicOperation;
import com.ctozatto.calcurest.calculator.domain.OperatorEnum;
import com.ctozatto.calcurest.rest.domain.OperationResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class RabbitMQSender {

	private RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.routingKey}")
	private String routingKey;
	
	@Autowired
	public RabbitMQSender(RabbitTemplate template) {
		this.rabbitTemplate = template;
	}
	
	public OperationResult sendAndReceive(String paramA, String paramB, OperatorEnum operator) {
		BigDecimal a = null;
		BigDecimal b = null;
		
		try {
			a = new BigDecimal(paramA);
			b = new BigDecimal(paramB);
		} catch(NumberFormatException e) {
			a = null;
			b = null;
		}
		BasicOperation operation = new BasicOperation(a, b, operator);
		
		String received = (String) rabbitTemplate.convertSendAndReceive(exchange, routingKey, operation);
		
		JsonObject json = new Gson().fromJson(received, JsonObject.class);
		
		return new OperationResult(json.get("result").getAsString(), json.get("error").getAsString());
	}
	
}
