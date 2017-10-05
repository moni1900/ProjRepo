package com.labcorp.pricecalculator.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.labcorp.pricecalculator.Item;
import com.labcorp.pricecalculator.OrderLine;

public class OrderLineTest {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetQuantity() throws Exception {	
		int quantitity = 10;
		OrderLine line = new OrderLine(new Item("imported box of chocolate", (float)10.00), 10);
		assertEquals(line.getQuantity(), 10);
	}
	
	@Test(expected = Exception.class)
	public void testExceptionForNullItem() throws Exception {
		OrderLine orderLine = new OrderLine(null,1);
	}
}
