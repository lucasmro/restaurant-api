package com.lucasmro.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.model.Order;

public class OrderDaoImpl implements OrderDao {
	MongoCollection collection;

	public OrderDaoImpl(MongoDBManager manager) {
		collection = manager.getCollection("orders");
	}

	@Override
	public void persist(Order order) {
		collection.save(order);
	}

	@Override
	public List<Order> findAllByTable(Integer table) {
		Iterable<Order> ordersIterator = collection.find("{table: #}", table).as(Order.class);

		// TODO: Convert from iterator to List
		List<Order> orders = new ArrayList<Order>();
		for (Order order : ordersIterator) {
			orders.add(order);
		}

		return (0 == orders.size()) ? null : orders;
	}

	@Override
	public Order findById(String id) {
		return collection.findOne(new ObjectId(id)).as(Order.class);
	}

	@Override
	public void updateStatusById(String id, OrderStatus status) {
		collection.update(new ObjectId(id)).with("{$set: {status: #}}", status);
	}
}
