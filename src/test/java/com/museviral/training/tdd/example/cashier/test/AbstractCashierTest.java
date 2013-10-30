/**
 * 
 */
package com.museviral.training.tdd.example.cashier.test;

import static org.junit.Assert.assertEquals;

import com.museviral.training.tdd.example.cashier.Cashier;

/**
 * 
 * 
 * @author Cyril
 * @since 0.1.0
 */
public abstract class AbstractCashierTest {

	protected Cashier cashier;

	protected void assertCheckoutAmountIs(double expected) {

		assertEquals("the amount needed to be paid should be", expected,
				cashier.calculate(), 0);

	}

	protected void initStandardPriceList() {

		cashier.registerItem("apple", 5);
		cashier.registerItem("orange", 3);

	}

	protected void assertCashierShouldHaveNItems(int expectedItemCount) {

		assertEquals("number of items in cashier", expectedItemCount,
				cashier.getCartItemCount());

	}

}
