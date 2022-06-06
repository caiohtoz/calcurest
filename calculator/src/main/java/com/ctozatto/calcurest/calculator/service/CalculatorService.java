package com.ctozatto.calcurest.calculator.service;

import java.math.BigDecimal;

import com.ctozatto.calcurest.calculator.domain.BasicOperation;

public class CalculatorService {

	public static BigDecimal calculate(BasicOperation operation) {
		BigDecimal result = null;
		BigDecimal a = operation.getOperand1();
		BigDecimal b = operation.getOperand2();
		
		switch(operation.getOperator()) {
		
			case ADDITION:
				result = sum(a, b);
				break;
				
			case SUBTRACTION:
				result = difference(a, b);
				break;
				
			case MULTIPLICATION:
				result = product(a, b);
				break;
				
			case DIVISION:
				result = quotient(a, b);
				break;
				
			default:
				break;
		
		}
		
		return result;
	}
	
	public static BigDecimal sum(BigDecimal a, BigDecimal b) {		
		return a.add(b);
	}
	
	public static BigDecimal difference(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}
	
	public static BigDecimal product(BigDecimal a, BigDecimal b) {
		return a.multiply(b);
	}
	
	public static BigDecimal quotient(BigDecimal a, BigDecimal b) {
		return a.divide(b);
	}
	
}
