package com.ctozatto.calcurest.calculator.message;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import com.ctozatto.calcurest.calculator.domain.BasicOperation;
import com.ctozatto.calcurest.calculator.service.CalculatorService;
import com.google.gson.JsonObject;

@Component
public class RabbitMQReceiver implements RabbitListenerConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);
    
	@Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
    public String receiveMessage(BasicOperation operation) {
        logger.debug("Operation received: " + operation.toString());
        
        BigDecimal result = null;
        JsonObject json = new JsonObject();
        
        if (operation != null)
        	try {
        		result = CalculatorService.calculate(operation);
        	} catch(Exception e) {
        		json.addProperty("result", "");
        		json.addProperty("error", "Error while calculating result. " + e.getMessage());
        	}
        
        if (result != null) {
        	json.addProperty("result", result.toString());
        	json.addProperty("error", "");
        }
        
        return json.toString();
    }
	
}
