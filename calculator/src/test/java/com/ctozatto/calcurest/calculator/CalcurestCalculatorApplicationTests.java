package com.ctozatto.calcurest.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctozatto.calcurest.calculator.service.CalculatorService;

@SpringBootTest("service.message=Calc test")
public class CalcurestCalculatorApplicationTests {

	@Autowired
	private CalculatorService calcService;
	
	@Test
	public void contextLoads() {
	  assertThat(calcService.message()).isNotNull();
	}
	
	@SpringBootApplication
	static class TestConfiguration {
	}

}
