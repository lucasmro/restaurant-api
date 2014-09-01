package com.lucasmro.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.lucasmro.restaurant.model.Product;

public class ProductDaoImpl implements ProductDao {
	MongoCollection collection;

	public ProductDaoImpl(MongoDBManager manager) {
		collection = manager.getCollection("products");
	}

	@Override
	public void persist(Product product) {
		collection.save(product);
	}

	@Override
	public List<Product> findAll() {
		Iterable<Product> productsIterator = collection.find().as(Product.class);

		// TODO: Convert from iterator to List
		List<Product> products = new ArrayList<Product>();
		for (Product product : productsIterator) {
			products.add(product);
		}

		return (0 == products.size()) ? null : products;
	}

	@Override
	public Product findById(String id) {
		return collection.findOne(new ObjectId(id)).as(Product.class);
	}
}
