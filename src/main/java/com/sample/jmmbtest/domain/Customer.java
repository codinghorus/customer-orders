package com.sample.jmmbtest.domain;

public class Customer {
	private int customerId;
	private int pririoty;
	private String address;
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", pririoty=" + pririoty
				+ ", address=" + address + "]";
	}
}
