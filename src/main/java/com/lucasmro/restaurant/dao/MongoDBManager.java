package com.lucasmro.restaurant.dao;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoDBManager {
	Jongo jongo;

	public MongoDBManager() {
		try {
			Mongo mongo = new Mongo("127.0.0.1", 27017);
			DB db = mongo.getDB("test");
			jongo = new Jongo(db);
		} catch (UnknownHostException e) {
		}
	}

	public MongoCollection getCollection(String name) {
		return jongo.getCollection(name);
	}
}
