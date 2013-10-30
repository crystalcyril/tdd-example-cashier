/**
 * 
 */
package com.museviral.training.tdd.example.cashier.rules.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.museviral.training.tdd.example.cashier.Item;
import com.museviral.training.tdd.example.cashier.RuleResult;
import com.museviral.training.tdd.example.cashier.rules.BuyNPayFixedAmount;

/**
 * 
 * 
 * @author Cyril
 * @since 0.1.0
 */
public class BuyNPayFixedAmountTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuyNothing() {

		BuyNPayFixedAmount rule = new BuyNPayFixedAmount(2, "apple", 9);

		List<Item> bucket = new ArrayList<Item>();
		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(0, ruleResult.getAmount(), 0);
		assertEquals(0, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testBuyOneApple() {

		BuyNPayFixedAmount rule = new BuyNPayFixedAmount(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(0, ruleResult.getAmount(), 0);
		assertEquals(0, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testBuyTwoApple() {

		BuyNPayFixedAmount rule = new BuyNPayFixedAmount(2, "apple", 1.1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(1.1, ruleResult.getAmount(), 0);
		assertEquals(2, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testBuyTwoIrrelevantItems() {

		BuyNPayFixedAmount rule = new BuyNPayFixedAmount(2, "apple", 1.1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("orange", 5));
		bucket.add(new Item("orange", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(0, ruleResult.getAmount(), 0);
		assertEquals(0, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testBuyThreeItems() {

		BuyNPayFixedAmount rule = new BuyNPayFixedAmount(2, "apple", 1.1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(1.1, ruleResult.getAmount(), 0);
		assertEquals(2, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testBuyFourItems() {

		BuyNPayFixedAmount rule = new BuyNPayFixedAmount(2, "apple", 1.1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(1.1, ruleResult.getAmount(), 0);
		assertEquals(2, ruleResult.getDiscountedItems().size());

	}

}
