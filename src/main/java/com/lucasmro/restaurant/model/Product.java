package com.lucasmro.restaurant.model;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.lucasmro.restaurant.enums.ProductType;

public class Product {
	@Id @ObjectId
	private String id;
	private String description;
	private ProductType type;
	private Double price;

	public Product() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", type="
				+ type + ", price=" + price + "]";
	}
}
