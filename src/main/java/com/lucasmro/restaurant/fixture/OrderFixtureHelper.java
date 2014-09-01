package com.lucasmro.restaurant.fixture;

import org.jongo.MongoCollection;

import com.lucasmro.restaurant.dao.MongoDBManager;

public class OrderFixtureHelper {
	public static String COLLECTION_NAME = "orders";

	public static void load(MongoDBManager manager) {
		MongoCollection collection = manager.getCollection(COLLECTION_NAME);
		collection.insert(OrderFixture.createTableOrderHamburguer());
		collection.insert(OrderFixture.createTableOrderDrink());
		collection.insert(OrderFixture.createTableOrderDessert());
		collection.insert(OrderFixture.createDeliveryOrder());
	}

	public static void drop(MongoDBManager manager) {
		MongoCollection collection = manager.getCollection(COLLECTION_NAME);
		collection.drop();
	}
}
