/**
 * 
 */
package com.museviral.training.tdd.example.cashier.impl;

import java.util.ArrayList;
import java.util.List;

import com.museviral.training.tdd.example.cashier.Item;
import com.museviral.training.tdd.example.cashier.RuleResult;

/**
 * Concrete implementation of <code>RuleResult</code>.
 * 
 * @author Cyril
 * @since 0.1.0
 */
public class RuleResultImpl implements RuleResult {

	List<Item> discountedItems = new ArrayList<Item>();

	double amount;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.museviral.training.tdd.helloworld.cashier.RuleResult#getAmount()
	 */
	public double getAmount() {
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.museviral.training.tdd.helloworld.cashier.RuleResult#getDiscountedItems
	 * ()
	 */
	public List<Item> getDiscountedItems() {
		return discountedItems;
	}

	public void setDiscountedItems(List<Item> discountedItems) {
		this.discountedItems = discountedItems;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "discountedItems=" + discountedItems + ", amount=" + amount;
	}

}
