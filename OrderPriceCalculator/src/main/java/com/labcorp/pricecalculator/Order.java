package com.labcorp.pricecalculator;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
    //Intialize orderLines (NullPointer Exception)
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public void add(OrderLine o){
		if (o == null) {
			System.out.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void clear() {
		this.orderLines.clear();
	}

}
