package com.lucasmro.restaurant.fixture;

import org.jongo.MongoCollection;

import com.lucasmro.restaurant.dao.MongoDBManager;

public class ProductFixtureHelper {
	public static String COLLECTION_NAME = "products";

	public static void load(MongoDBManager manager) {
		MongoCollection collection = manager.getCollection(COLLECTION_NAME);
		collection.insert(ProductFixture.createXEggHamburguer());
		collection.insert(ProductFixture.createCokeDrink());
		collection.insert(ProductFixture.createIceCreamDessert());
	}

	public static void drop(MongoDBManager manager) {
		MongoCollection collection = manager.getCollection(COLLECTION_NAME);
		collection.drop();
	}
}
