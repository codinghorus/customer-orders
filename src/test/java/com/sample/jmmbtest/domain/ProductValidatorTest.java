package com.sample.jmmbtest.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductValidatorTest {
	
	@Test
	public void validationErrorsForTest() {
		String productName = null;
		int importance = -1;
		Product product = new Product(productName, importance);
		ProductValidator productValidator = new ProductValidator(product);
		
		assertFalse("ProductValidator is invalid without product name or importance out of range", 
				productValidator.isValid());
		
		productValidator = new ProductValidator(new Product("New Product", 50));
		assertTrue("ProductValidator is valid with product name and importance between 0 and 100", 
				productValidator.isValid());
	}
}
