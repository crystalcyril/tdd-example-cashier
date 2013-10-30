/**
 * 
 */
package com.museviral.training.tdd.example.cashier.rules.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.museviral.training.tdd.example.cashier.Item;
import com.museviral.training.tdd.example.cashier.RuleResult;
import com.museviral.training.tdd.example.cashier.rules.BuyNGetMFree;

/**
 * 
 * 
 * @author Cyril
 * 
 */
public class BuyNGetMFreeTest {

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
	public void testNoItem() {

		BuyNGetMFree rule = new BuyNGetMFree(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(0, ruleResult.getAmount(), 0);
		assertEquals(0, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testOneItem() {

		BuyNGetMFree rule = new BuyNGetMFree(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(0, ruleResult.getAmount(), 0);
		assertEquals(0, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testTwoItem() {

		BuyNGetMFree rule = new BuyNGetMFree(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(0, ruleResult.getAmount(), 0);
		assertEquals(0, ruleResult.getDiscountedItems().size());

	}

	@Test
	public void testOneSetOfItemsMatched() {

		BuyNGetMFree rule = new BuyNGetMFree(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(10, ruleResult.getAmount(), 0);
		assertEquals(3, ruleResult.getDiscountedItems().size());
		for (Item discountedItem : ruleResult.getDiscountedItems()) {
			assertEquals("apple", discountedItem.getItemCode());
			assertEquals(5, discountedItem.getPrice(), 0);
		}

	}

	@Test
	public void testOneSetOfItemsMatchedWithRemaining() {

		BuyNGetMFree rule = new BuyNGetMFree(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(10, ruleResult.getAmount(), 0);
		assertEquals(3, ruleResult.getDiscountedItems().size());
		for (Item discountedItem : ruleResult.getDiscountedItems()) {
			assertEquals("apple", discountedItem.getItemCode());
			assertEquals(5, discountedItem.getPrice(), 0);
		}

	}	
	
	@Test
	public void testTwoSetOfItemsMatched() {

		BuyNGetMFree rule = new BuyNGetMFree(2, "apple", 1);

		List<Item> bucket = new ArrayList<Item>();
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));
		bucket.add(new Item("apple", 5));

		RuleResult ruleResult = rule.process(bucket);

		assertNotNull(ruleResult);
		assertEquals(20, ruleResult.getAmount(), 0);
		assertEquals(6, ruleResult.getDiscountedItems().size());
		for (Item discountedItem : ruleResult.getDiscountedItems()) {
			assertEquals("apple", discountedItem.getItemCode());
			assertEquals(5, discountedItem.getPrice(), 0);
		}

	}
}
