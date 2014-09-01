package com.lucasmro.restaurant.dao;

import java.util.List;

import com.lucasmro.restaurant.model.Product;

public interface ProductDao {
	public void persist(Product product);

	public List<Product> findAll();

	public Product findById(String id);
}
