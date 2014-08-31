package com.lucasmro.restaurant.fixture;

import com.lucasmro.restaurant.enums.ProductType;
import com.lucasmro.restaurant.model.Address;
import com.lucasmro.restaurant.model.Delivery;
import com.lucasmro.restaurant.model.Order;
import com.lucasmro.restaurant.model.OrderItem;
import com.lucasmro.restaurant.model.Product;

public class OrderFixture {
	public static Order createTableOrderHamburguer() {
		Product product = new Product();
		product.setId(1);
		product.setType(ProductType.HAMBURGUER);
		product.setDescription("X-EGG");
		product.setPrice(10.5);

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
		Product product = new Product();
		product.setId(1);
		product.setType(ProductType.DRINK);
		product.setDescription("Coca-Cola");
		product.setPrice(4.0);

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
		Product product = new Product();
		product.setId(1);
		product.setType(ProductType.HAMBURGUER);
		product.setDescription("X-EGG");
		product.setPrice(10.5);

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
