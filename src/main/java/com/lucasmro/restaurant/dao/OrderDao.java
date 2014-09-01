package com.lucasmro.restaurant.dao;

import java.util.List;

import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.model.Order;

public interface OrderDao {
	public void persist(Order order);

	public List<Order> findAllByTable(Integer table);

	public Order findById(String id);

	public void updateStatusById(String id, OrderStatus status);
}
