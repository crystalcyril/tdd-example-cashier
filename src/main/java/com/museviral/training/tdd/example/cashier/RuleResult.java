/**
 * 
 */
package com.museviral.training.tdd.example.cashier;

import java.util.List;

/**
 * @author Cyril
 * 
 */
public interface RuleResult {

	double getAmount();

	List<Item> getDiscountedItems();

}
