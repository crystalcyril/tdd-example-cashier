/**
 * 
 */
package com.museviral.training.tdd.example.cashier.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.museviral.training.tdd.example.cashier.Cashier;
import com.museviral.training.tdd.example.cashier.rules.BuyNGetMFree;

/**
 * 
 * 
 * @author Cyril
 * 
 */
public class CashierTest {

	Cashier cashier;

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

		assertEquals(0, cashier.getCartItemCount());

		assertCheckoutAmountIs(0);

	}

	@Test
	public void testBuyOneApple() {

		initStandardPriceList();

		cashier.addItem("apple");

		assertEquals(1, cashier.getCartItemCount());

		assertCheckoutAmountIs(5);

	}

	@Test
	public void testBuyTwoApple() {

		initStandardPriceList();

		cashier.addItem("apple");
		cashier.addItem("apple");

		assertEquals(2, cashier.getCartItemCount());

		assertCheckoutAmountIs(10);

	}

	@Test
	public void testBuyOneAppleOneOrange() {

		initStandardPriceList();

		cashier.addItem("apple");
		cashier.addItem("orange");

		assertEquals(2, cashier.getCartItemCount());

		assertCheckoutAmountIs(8);

	}

	@Test
	public void testBuy2AppleGetOneFree() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree(2, "apple", 1)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertEquals(3, cashier.getCartItemCount());

		assertCheckoutAmountIs(10);

	}

	@Test
	public void testBuy2AppleGetOneFree_2Times() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree(2, "apple", 1)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertEquals(6, cashier.getCartItemCount());

		assertCheckoutAmountIs(20);

	}

	@Test
	public void testBuy2AppleGetOneFree_Buy4Apple() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree(2, "apple", 1)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertEquals(4, cashier.getCartItemCount());

		assertCheckoutAmountIs(15);

	}

	@Test
	public void testBuy1AppleGet2Free_Buy4Apple() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree(1, "apple", 2)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertEquals(3, cashier.getCartItemCount());

		assertCheckoutAmountIs(5);

	}

	@Test
	public void testBuy3AppleGet2Free_Buy5Apple() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree(3, "apple", 2)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");

		assertEquals(5, cashier.getCartItemCount());

		assertCheckoutAmountIs(15);

	}

	@Test
	public void testBuy3AppleGet2OrangeFree() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree("apple", 3, "orange", 2)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("orange");
		cashier.addItem("orange");

		assertEquals(5, cashier.getCartItemCount());

		assertCheckoutAmountIs(15);

	}

	@Test
	public void testBuy3AppleGet2OrangeFree_2Apple3Orange() {

		initStandardPriceList();

		// set rules

		assertTrue(cashier.addRule(new BuyNGetMFree("apple", 3, "orange", 2)));

		cashier.addItem("apple");
		cashier.addItem("apple");
		cashier.addItem("orange");
		cashier.addItem("orange");
		cashier.addItem("orange");

		assertEquals(5, cashier.getCartItemCount());

		assertCheckoutAmountIs(10 + 9);

	}

	protected void assertCheckoutAmountIs(double expected) {

		assertEquals("the amount needed to be paid should be", expected,
				cashier.calculate(), 0);

	}

	protected void initStandardPriceList() {
		cashier.registerItem("apple", 5);
		cashier.registerItem("orange", 3);
	}

}
