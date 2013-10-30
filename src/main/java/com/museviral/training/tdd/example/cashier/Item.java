/**
 * 
 */
package com.museviral.training.tdd.example.cashier;

/**
 * Represent an item.
 * 
 * @author Cyril
 * @since 0.1.0
 */
public class Item {

	protected String itemCode;

	protected double price;

	public Item(String itemCode, double price) {
		super();

		if (itemCode == null) {
			throw new IllegalArgumentException("item code should not be null");
		} else if (price < 0) {
			throw new IllegalArgumentException("price should not be negative");
		}

		this.itemCode = itemCode;
		this.price = price;
	}

	public String getItemCode() {
		return itemCode;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", price=" + price + "]";
	}

}
