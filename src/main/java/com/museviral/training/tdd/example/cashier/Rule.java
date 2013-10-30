/**
 * 
 */
package com.museviral.training.tdd.example.cashier;

import java.util.List;

/**
 * Defines a rule for cashier.
 * <p>
 * 
 * A rule is a logic class which is capable of calculating discount of passed in
 * object.
 * 
 * <p>
 * Implementation of rule object should be stateless.
 * 
 * @author Cyril
 * @since 0.1.0
 */
public interface Rule {

	/**
	 * Process the given list of items. The calculated result will be returned
	 * as an object.
	 * 
	 * @param items
	 * @return
	 */
	RuleResult process(List<Item> items);

}
