package com.labcorp.pricecalculator;
/*
 * represents an order line which contains the @link Item and the quantity.
 *
 */
public class Item {
	private String description;
	private float price;
	
	/*
	 * @param item Item of the order
	 * 
	 * @param quantity Quantity of the item
	 */
	public Item(String description, float price) {
		super();
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

}
