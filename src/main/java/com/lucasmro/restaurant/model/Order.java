package com.lucasmro.restaurant.model;

import java.util.ArrayList;
import java.util.List;

import com.lucasmro.restaurant.enums.OrderStatus;

public class Order {
	private Integer id;
	private OrderStatus status;
	private List<OrderItem> items;
	private Integer table;
	private Delivery delivery;
	private Double total;
	// TODO: createdAt, updatedAt, table, delivery, notes

	public Order() {
		this.total = 0.0;
		this.status = OrderStatus.WAITING;
		this.items = new ArrayList<OrderItem>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public void addItem(OrderItem item) {
		this.items.add(item);
	}

	public Integer getTable() {
		return table;
	}

	public void setTable(Integer table) {
		this.table = table;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", items=" + items
				+ ", table=" + table + ", delivery=" + delivery + ", total="
				+ total + "]";
	}
}
