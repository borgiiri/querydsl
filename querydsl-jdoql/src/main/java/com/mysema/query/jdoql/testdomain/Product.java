/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.jdoql.testdomain;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Definition of a Product Represents a product, and contains the key aspects of the item.
 */
@PersistenceCapable
public class Product {
	private String name = null;

	private String description = null;

	private double price = 0.0;

	protected Product() {
	}

	public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "Product : " + name + " [" + description + "]";
	}
}