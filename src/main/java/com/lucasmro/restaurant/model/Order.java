package com.lucasmro.restaurant.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.serialization.Group;

@JsonInclude(Include.NON_NULL)
public class Order {
	@Id @ObjectId
	private String id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@JsonIgnore
	@JsonView(Group.Private.class)
	public boolean isValid() {
		// TODO: Apply visitor pattern to validate all rules
		return (null != this.getItems() && this.getItems().size() > 0) &&
			   (this.getTotal() > 0.0) &&
			   !(null != this.getTable() && null != this.getDelivery()) && // Should return false when table number and delivery are both set.
			   (null != this.getTable() || null != this.getDelivery());
		// TODO: Validate address and delivery
	}
}
