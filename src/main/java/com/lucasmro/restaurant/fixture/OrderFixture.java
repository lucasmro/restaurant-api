package com.lucasmro.restaurant.fixture;

import com.lucasmro.restaurant.model.Address;
import com.lucasmro.restaurant.model.Delivery;
import com.lucasmro.restaurant.model.Order;
import com.lucasmro.restaurant.model.OrderItem;
import com.lucasmro.restaurant.model.Product;

public class OrderFixture {
	public static Order createTableOrderHamburguer() {
		Product product = ProductFixture.createXEggHamburguer();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(2);

		Order order = new Order();
		order.setTable(5);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		return order;
	}

	public static Order createTableOrderDrink() {
		Product product = ProductFixture.createCocaColaDrink();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(1);

		Order order = new Order();
		order.setTable(5);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		return order;
	}

	public static Order createDeliveryOrder() {
		Product product = ProductFixture.createXEggHamburguer();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(2);

		Address address = new Address();
		address.setStreet("Rua Guararapes");
		address.setNumber("100");
		address.setComplement("APTO 302");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setZip("04561-000");

		Delivery delivery = new Delivery();
		delivery.setFullname("Lucas Michelini Reis de Oliveira");
		delivery.setEmail("lucasmro@gmail.com");
		delivery.setAddress(address);

		Order order = new Order();
		order.setDelivery(delivery);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		return order;
	}
}
