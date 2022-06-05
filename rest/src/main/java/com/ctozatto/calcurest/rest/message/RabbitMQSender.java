package com.ctozatto.calcurest.rest.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ctozatto.calcurest.calculator.domain.BasicOperation;

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
	
	public void send(BasicOperation operation) {
		rabbitTemplate.convertAndSend(exchange, routingKey, operation);
	}
	
}
