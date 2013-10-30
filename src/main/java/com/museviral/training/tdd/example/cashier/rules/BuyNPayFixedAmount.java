/**
 * 
 */
package com.museviral.training.tdd.example.cashier.rules;

import java.util.ArrayList;
import java.util.List;

import com.museviral.training.tdd.example.cashier.Item;
import com.museviral.training.tdd.example.cashier.Rule;
import com.museviral.training.tdd.example.cashier.RuleResult;
import com.museviral.training.tdd.example.cashier.impl.RuleResultImpl;

/**
 * @author Cyril
 * 
 */
public class BuyNPayFixedAmount implements Rule {

	protected int requiredPurchaseQty;

	protected String itemCode;

	protected double amountToPay;

	public BuyNPayFixedAmount(int requiredPurchaseQty, final String itemCode,
			double amountToPay) {
		this.requiredPurchaseQty = requiredPurchaseQty;
		this.itemCode = itemCode;
		this.amountToPay = amountToPay;

		if (itemCode == null)
			throw new IllegalArgumentException("itemCode should not be null");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.museviral.training.tdd.example.cashier.Rule#process(java.util.List)
	 */
	@Override
	public RuleResult process(List<Item> items) {

		RuleResultImpl r = new RuleResultImpl();
		List<Item> matchedItems = new ArrayList<Item>();

		for (Item item : items) {
			if (getItemCode().equals(item.getItemCode())) {
				matchedItems.add(item);
			}

			// stop the loop if required number of items to buy is met
			if (getRequiredPurchaseQty() == matchedItems.size()) {
				break;
			}

		}

		// if purchase criteria fulfilled, we calculate the discount.
		if (getRequiredPurchaseQty() == matchedItems.size()) {
			r.setAmount(getAmountToPay());
			r.setDiscountedItems(matchedItems);
		}

		return r;
	}

	public int getRequiredPurchaseQty() {
		return requiredPurchaseQty;
	}

	public String getItemCode() {
		return itemCode;
	}

	public double getAmountToPay() {
		return amountToPay;
	}
}
