package com.labcorp.pricecalculator;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator {

    //rounding method returns precision upto two decimals for any given double
	public static double rounding(double value) {
		return ((int)(value * 100)) / 100.0;
	}
	/**
	 * receives a collection of orders. For each order, iterates on the order lines and calculate the total price which
	 * is the item's price * quantity * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without taxes for this order
	 */
	public double calculate(Map<String, Order> orders) {

		if(orders == null) {
			throw new IllegalArgumentException("orders is Null.");
		}

		double grandTotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : orders.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");

			Order order = entry.getValue();

			double totalTax = 0;
			double total = 0;

			for(OrderLine line : order.getOrderLines()) {
				double tax = 0;
				Item item = line.getItem();
				tax = calculateTax(item);
				double totalPrice = item.getPrice() + tax;
				System.out.println(item.getDescription() + ": " + roundToTwoDecimal(totalPrice));

				totalTax += tax;
				total += item.getPrice();
			}

			System.out.println("Sales Tax: " + roundToTwoDecimal(totalTax) /*Math.floor(totalTax)*/);

			System.out.println("Total: " + roundToTwoDecimal(total) /*Math.floor(total * 100) / 100*/);
			grandTotal += total;
		}

		System.out.println("Sum of orders: " + roundToTwoDecimal(grandTotal));

		return grandTotal;
	}

	public static double roundToTwoDecimal(Double value) {
		return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	// Calculate the taxes
	public static double calculateTax(Item item) {
		double tax;

		if (item.getDescription().toLowerCase().contains("imported")) {
			// imported items
			tax = roundToTwoDecimal(item.getPrice() * 0.15); // Extra 5% tax on
		} else {
			tax = roundToTwoDecimal(item.getPrice() * 0.10);
		}
		return tax;
	}

}
