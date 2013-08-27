package com.sample.jmmbtest.domain;

import java.util.HashMap;
import java.util.Map;

public class ProductValidator {
	private Map<String, String> validationErrors;

	public ProductValidator(Product product) {
		validationErrors = new HashMap<String, String>();

		String errorForName = validationErroFor(product.getProductName());
		if (errorForName != "")
			validationErrors.put("ProductName", errorForName);

		String errorForImportance = validationErroFor(product.getImportance());
		if (errorForImportance != "")
			validationErrors.put("Importance", errorForImportance);
	}

	public boolean isValid() {
		return validationErrors.isEmpty();
	}
	
	public Map<String, String> validationErrors() {
		return validationErrors;
	}

	private String validationErroFor(String productName) {
		String errorMessage = productName == null || productName.isEmpty() ? "Product name is mandatory"
				: "";
		return errorMessage;
	}

	private String validationErroFor(int importance) {
		String errorMessage = importance < 0 && importance > 100 ? "Product importance should be between 0 and 100"
				: "";
		return errorMessage;
	}
}
