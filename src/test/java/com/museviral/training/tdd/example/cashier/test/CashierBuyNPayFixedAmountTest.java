/**
 * 
 */
package com.museviral.training.tdd.example.cashier.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.museviral.training.tdd.example.cashier.Cashier;
import com.museviral.training.tdd.example.cashier.rules.BuyNPayFixedAmount;

/**
 * 
 * 
 * @author Cyril
 * @since 0.1.0
 */
public class CashierBuyNPayFixedAmountTest extends AbstractCashierTest {

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
	public void testInitialState() {

		assertCashierShouldHaveNItems(0);

		assertCheckoutAmountIs(0);

	}

	@Test
	public void testBuyOneApple() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(2, "apple", 8)));

		cashier.addItem("apple");

		assertCashierShouldHaveNItems(1);

		assertCheckoutAmountIs(5);

	}

	@Test
	public void testBuyTwoApple() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(2, "apple", 8)));

		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(2);
		assertCheckoutAmountIs(8);

	}

	@Test
	public void testBuyThreeApple() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(2, "apple", 8)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(3);
		assertCheckoutAmountIs(8 + 5);

	}

	@Test
	public void testBuyFourApple() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(2, "apple", 8)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(4);
		assertCheckoutAmountIs(8 + 8);

	}

	@Test
	public void testBuyFiveApple() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(2, "apple", 8)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(5);
		assertCheckoutAmountIs(8 + 8 + 5);

	}

	@Test
	public void testBuyThreeApple_Buy3Pay9() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(3, "apple", 9)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(3);
		assertCheckoutAmountIs(9);

	}

	@Test
	public void testBuyFourApple_Buy3Pay9() {

		initStandardPriceList();

		assertTrue(cashier.addRule(new BuyNPayFixedAmount(3, "apple", 9)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertCashierShouldHaveNItems(4);
		assertCheckoutAmountIs(9 + 5);

	}

}
