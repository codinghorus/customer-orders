package com.sample.jmmbtest.domain;

public class Product {
	private int productId;
	private String productName;
	private int importance;

	public Product(String proudctName, int importance) {
		productName = proudctName;
		this.importance = importance;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}
}
