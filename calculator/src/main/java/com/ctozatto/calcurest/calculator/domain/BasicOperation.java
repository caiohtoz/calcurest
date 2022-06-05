package com.ctozatto.calcurest.calculator.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = BasicOperation.class)
public class BasicOperation implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal operand1;
	private BigDecimal operand2;
	private OperatorEnum operator;
	
	public BasicOperation(BigDecimal operand1, BigDecimal operand2, OperatorEnum operator) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operator = operator;
	}
	
	public BasicOperation() {
	}
	
	public BigDecimal getOperand1() {
		return operand1;
	}
	public void setOperand1(BigDecimal operand1) {
		this.operand1 = operand1;
	}
	
	public BigDecimal getOperand2() {
		return operand2;
	}
	public void setOperand2(BigDecimal operand2) {
		this.operand2 = operand2;
	}
	
	public OperatorEnum getOperator() {
		return operator;
	}
	public void setOperator(OperatorEnum operator) {
		this.operator = operator;
	}
	
	@Override
	public String toString() {
		return "BasicOperation{" +
				"operand1='" + operand1 + '\'' +
                ", operand2='" + operand2 + '\'' +
                ", operator='" + operator + '\'' +
                '}';
	}
	
}
