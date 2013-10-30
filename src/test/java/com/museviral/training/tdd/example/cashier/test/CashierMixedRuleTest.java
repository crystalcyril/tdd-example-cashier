/**
 * 
 */
package com.museviral.training.tdd.example.cashier.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.museviral.training.tdd.example.cashier.Cashier;
import com.museviral.training.tdd.example.cashier.rules.BuyNGetMFree;
import com.museviral.training.tdd.example.cashier.rules.BuyNPayFixedAmount;

/**
 * 
 * 
 * @author Cyril
 */
public class CashierMixedRuleTest extends AbstractCashierTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cashier = new Cashier();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// destroy it
		cashier = null;
	}

	@Test
	public void testBuy2AppleGet1Free_And_Buy3OrangePay6() {

		this.initStandardPriceList();

		// buy 2 apple, get 1 apple free
		cashier.addRule(new BuyNGetMFree(2, "apple", 1));
		cashier.addRule(new BuyNPayFixedAmount(3, "orange", 6));

		// now go shopping.
		cashier.addItem("orange");
		cashier.addItem("apple");
		cashier.addItem("orange");
		cashier.addItem("apple");
		cashier.addItem("orange");

		assertCashierShouldHaveNItems(5);

		assertCheckoutAmountIs(10 + 6);

	}

	/**
	 * In this test, we add two rules, however, one of the rule will not be
	 * effective on cashier checkout since the number of items bought is not
	 * enough to meet the rule's criteria.
	 */
	@Test
	public void testBuySameItemsTwoRulesOnlyOneRuleIsOkDueToLackOfPurchasingQty() {

		this.initStandardPriceList();

		// buy 2 apple, get 1 apple free
		cashier.addRule(new BuyNGetMFree(4, "apple", 2));
		cashier.addRule(new BuyNPayFixedAmount(2, "apple", 7));

		// now go shopping. buy 5. Only the "BuyNPayFixedAmount" rule
		// will be effective
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(5);

		assertCheckoutAmountIs(7 + 7 + 5);

	}

	/**
	 * In this test, we add two rules, and the cashier should pick the rule
	 * which could help the customer to make maximum savings / discount.
	 */
	@Test
	public void testCashierDetermineTheBestRuleOnSameItems_1() {

		this.initStandardPriceList();

		// two rules:
		// 1. buy 2 apple, get 1 apple free. Discount: $15 -> $10, saved $5.
		// 2. buy 3 apple, pay $11. Discount: $15 -> $11, saved $4.
		// therefore, cashier should choose rule 1.
		cashier.addRule(new BuyNGetMFree(2, "apple", 1));
		cashier.addRule(new BuyNPayFixedAmount(3, "apple", 11));

		// now go shopping. buy 5. Only the "BuyNPayFixedAmount" rule
		// will be effective
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(3);

		assertCheckoutAmountIs(10);

	}

	/**
	 * This is the variant of
	 * {@link #testCashierDetermineTheBestRuleOnSameItems_1()}, which in this
	 * test case, we change rule #2 to pay $9 instead of $11.
	 */
	@Test
	public void testCashierDetermineTheBestRuleOnSameItems_2() {

		this.initStandardPriceList();

		// two rules:
		// 1. buy 2 apple, get 1 apple free. Discount: $15 -> $10, saved $5.
		// 2. buy 3 apple, pay $11. Discount: $15 -> $9, saved $6.
		// therefore, cashier should choose rule 2.
		cashier.addRule(new BuyNGetMFree(2, "apple", 1));
		cashier.addRule(new BuyNPayFixedAmount(3, "apple", 9));

		// now go shopping. buy 5. Only the "BuyNPayFixedAmount" rule
		// will be effective
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(3);

		assertCheckoutAmountIs(9);

	}

}
