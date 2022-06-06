package com.ctozatto.calcurest.rest.domain;

public class OperationResult {

	//Error will be empty when result is not, and vice-versa
	private String result;
	private String error;
	
	public OperationResult(String result, String error) {
		this.result = result;
		this.error = error;
	}
	
	public OperationResult() {
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
