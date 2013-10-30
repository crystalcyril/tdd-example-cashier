/**
 * 
 */
package com.museviral.training.tdd.example.cashier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The main engine of the cashier.
 * <p>
 * 
 * For simplicity, this is a concrete class instead of an interface.
 * 
 * @author Cyril
 * @since 0.1.0
 */
public class Cashier {

	Map<String, Item> priceList = new HashMap<String, Item>();

	List<Item> cart = new ArrayList<Item>();

	Set<Rule> rules = new HashSet<Rule>();

	ArrayList<Item> cartWorkspace;

	public Cashier() {
		super();
	}

	public int getCartItemCount() {
		return cart.size();
	}

	public double calculate() {

		// our strategy:
		// - put all items in another collection
		// - let the rule objects to pick their favorites

		double amount = 0.0;

		// put all items into a workspace.
		cartWorkspace = new ArrayList<Item>(cart);

		Map<Rule, RuleResult> ruleResults = new HashMap<Rule, RuleResult>();

		do {

			ruleResults.clear();

			// now let the rules to process the items
			for (Rule rule : rules) {

				List<Item> ruleItems = new ArrayList<Item>(cartWorkspace);

				// after calling this method, the rule will process a result
				// which
				// contains:
				// - the total amount of price of goods discounted.
				// - the list of items which are elegible for the rule.
				// - the remaining list of items which are out of this rule's
				// scope.
				RuleResult ruleResult = rule.process(ruleItems);

				if (ruleResult.getDiscountedItems().size() > 0) {
					ruleResults.put(rule, ruleResult);
				}

			}

			// now we know all rule's preference. we pick the rule which helps
			// the customer to save most.
			if (!ruleResults.isEmpty()) {

				Map.Entry<Rule, RuleResult> bestRule = null;

				for (Map.Entry<Rule, RuleResult> entry : ruleResults.entrySet()) {

					if (bestRule == null) {
						bestRule = entry;
					} else {
						if (entry.getValue().getAmount() < bestRule.getValue()
								.getAmount()) {
							bestRule = entry;
						}
					}

				}

				List<Item> discountedItems = bestRule.getValue()
						.getDiscountedItems();
				for (Item item : discountedItems) {
					cartWorkspace.remove(item);
				}
				amount += bestRule.getValue().getAmount();

			}

		} while (!ruleResults.isEmpty());

		for (Item item : cartWorkspace) {
			amount += item.getPrice();
		}

		return amount;
	}

	public void registerItem(String itemCode, double price) {

		Item item = new Item(itemCode, price);
		this.priceList.put(itemCode, item);

	}

	public boolean addItem(String itemCode) {

		if (!priceList.containsKey(itemCode)) {
			return false;
		}

		Item item = priceList.get(itemCode);

		cart.add(item);
		return true;

	}

	public boolean addRule(Rule rule) {

		return rules.add(rule);

	}

}
