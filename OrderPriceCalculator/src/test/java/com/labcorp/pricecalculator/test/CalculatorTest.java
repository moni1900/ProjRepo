package com.labcorp.pricecalculator.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.labcorp.pricecalculator.Calculator;
import com.labcorp.pricecalculator.Order;
import com.labcorp.pricecalculator.OrderLine;
import com.labcorp.pricecalculator.Item;

public class CalculatorTest extends Calculator {

	private Calculator caluculator;

	@Before
	public void setUp() throws Exception {
		caluculator = new Calculator();
	}

	@After
	public void tearDown() throws Exception {
		caluculator = null;
	}

	@Test
	public void testCalculate() throws Exception {
		Map<String, Order> orders = new HashMap<String, Order>();

		Order ord = new Order();

		ord.add(new OrderLine(new Item("book", 10), 1));
		ord.add(new OrderLine(new Item("music CD", 10), 1));
		ord.add(new OrderLine(new Item("chocolate bar", 10), 1));

		orders.put("Order 1", ord);
		
		double totalPrice = caluculator.calculate(orders);
		assertEquals("Expected result not returned",30, totalPrice,.01);
		
	}

}
