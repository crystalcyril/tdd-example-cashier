/**
 * 
 */
package com.museviral.training.tdd.example.cashier.rules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.museviral.training.tdd.example.cashier.Item;
import com.museviral.training.tdd.example.cashier.Rule;
import com.museviral.training.tdd.example.cashier.RuleResult;
import com.museviral.training.tdd.example.cashier.impl.RuleResultImpl;

/**
 * 
 * 
 * @author Cyril
 */
public class BuyNGetMFree implements Rule {

	private String purchaseItemCode;

	private int requireBuyingQty;

	private int freeQty;
	
	private String freeItemCode;

	
	public BuyNGetMFree(String purchaseItemCode, int requireBuyingQty,
			String freeItemCode, int freeQty) {
		this.purchaseItemCode = purchaseItemCode;
		this.requireBuyingQty = requireBuyingQty;
		this.freeItemCode = freeItemCode;
		this.freeQty = freeQty;
	}
	
	public BuyNGetMFree(String purchaseItemCode, int requireBuyingQty,
			int freeQty) {
		this(purchaseItemCode, requireBuyingQty, purchaseItemCode, freeQty);
	}

	public String getPurchaseItemCode() {
		return purchaseItemCode;
	}

	public int getRequireBuyingQty() {
		return requireBuyingQty;
	}

	public int getFreeQty() {
		return freeQty;
	}

	protected RuleResult process2(List<Item> ruleItems) {

		RuleResultImpl result = new RuleResultImpl();

		List<Item> workspace = new ArrayList<Item>(ruleItems);
		List<Item> discountedItems = new ArrayList<Item>();

		double totalDiscountAmount = 0;

		boolean ruleHit = false;
		do {

			List<Item> iterationList = new ArrayList<Item>(workspace);

			// we assume the rule is never hit.
			ruleHit = false;

			// int purchasedItemCount = 0;
			// int redeemItemCount = 0;

			List<Item> hitPurchaseItems = extractPurchaseItems(
					this.getPurchaseItemCode(), iterationList,
					this.getRequireBuyingQty());
			if (hitPurchaseItems.size() != getRequireBuyingQty()) {
				break;
			}

			// purchase criteria fulfilled
			List<Item> redeemItem = extractPurchaseItems(
					this.getRedemptableItemCode(), iterationList,
					this.getFreeQty());
			if (redeemItem.size() != this.getFreeQty()) {
				// rule not fulfilled
				break;
			}

			// all criteria fulfilled.

			// calculate amount
			totalDiscountAmount += this.getRequireBuyingQty()
					* hitPurchaseItems.get(0).getPrice();

			// save the list of discounted items.
			discountedItems.addAll(hitPurchaseItems);
			discountedItems.addAll(redeemItem);
			workspace = new ArrayList<Item>(iterationList);
			ruleHit = true;

		} while (ruleHit);

		result.setAmount(totalDiscountAmount);
		result.setDiscountedItems(discountedItems);
		return result;

	}

	public List<Item> extractPurchaseItems(String targetCode, List<Item> list,
			int required) {

		List<Item> result = new ArrayList<Item>();

		int actualHitCount = 0;
		for (Item i : list) {
			if (targetCode.equals(i.getItemCode())) {
				actualHitCount++;
			}
			if (actualHitCount == required) {
				break;
			}
		}

		if (actualHitCount != required)
			return result;

		Iterator<Item> iter = list.iterator();
		while (iter.hasNext()) {

			Item i = iter.next();
			if (targetCode.equals(i.getItemCode())) {
				result.add(i);
				iter.remove();
				actualHitCount--;
			}

			if (actualHitCount == 0)
				break;

		}

		return result;

	}

	public RuleResult process(List<Item> ruleItems) {

		return process2(ruleItems);

	}

	protected List<Item> collectRedemptionItems(List<Item> items) {

		List<Item> workspace = new ArrayList<Item>(items);
		List<Item> result = new ArrayList<Item>();

		Iterator<Item> iter = workspace.iterator();
		while (iter.hasNext() && result.size() < this.getFreeQty()) {

			Item item = iter.next();
			if (item.getItemCode().equals(this.getRedemptableItemCode())) {
				result.add(item);
				iter.next();
			}

		}

		return result;

	}

	public String getRedemptableItemCode() {
		return freeItemCode;
	}

}
