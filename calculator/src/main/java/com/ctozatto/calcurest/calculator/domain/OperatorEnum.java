package com.ctozatto.calcurest.calculator.domain;

public enum OperatorEnum {

	ADDITION(1), SUBTRACTION(2), MULTIPLICATION(3), DIVISION(4);
	
	private int id;
	
	OperatorEnum(int value) {
		setId(value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
