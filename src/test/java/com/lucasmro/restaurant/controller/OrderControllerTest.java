package com.lucasmro.restaurant.controller;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Test;

import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.enums.ProductType;
import com.lucasmro.restaurant.model.Address;
import com.lucasmro.restaurant.model.Delivery;
import com.lucasmro.restaurant.model.Order;
import com.lucasmro.restaurant.model.OrderItem;
import com.lucasmro.restaurant.model.Product;

public class OrderControllerTest {
	private final static String ROUTE_ORDERS = "/orders/";
	private final static String ROUTE_ORDERS_ID = "/orders/{id}";
	private final static String ROUTE_ORDERS_ID_STATUS = "/orders/{id}/{status}";

	@Test
	public void testPostOrderShouldReturnUnsupportedMediaTypeWhenPayloadIsEmpty() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			statusCode(415).
		when().
			post(ROUTE_ORDERS);
	}

	@Test
	public void testPostOrderShouldReturnBadRequestWhenMandatoryFieldsAreBlankOrInvalid() throws JsonGenerationException, JsonMappingException, IOException {
		Order order = new Order();

		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(order);

		given().
			contentType(MediaType.APPLICATION_JSON).
			body(json).
		expect().
			statusCode(400).
		when().
			post(ROUTE_ORDERS);
	}

	@Test
	public void testPostTableOrderShouldReturnCreatedWhenOrderIsValid() throws JsonGenerationException, JsonMappingException, IOException {
		// TODO: Create data fixture

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

		// TODO: Create JsonConverter class to convert from Object to JSON and from JSON to Object.
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(order);

		given().
			contentType(MediaType.APPLICATION_JSON).
			body(json).
		expect().
			statusCode(201).
		when().
			post(ROUTE_ORDERS);
	}

	@Test
	public void testPostDeliveryOrderShouldReturnCreatedWhenOrderIsValid() throws JsonGenerationException, JsonMappingException, IOException {
		// TODO: Create data fixture

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

		// TODO: Create JsonConverter class to convert from Object to JSON and from JSON to Object.
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(order);

		given().
			contentType(MediaType.APPLICATION_JSON).
			body(json).
		expect().
			statusCode(201).
		when().
			post(ROUTE_ORDERS);
	}

	@Test
	public void testGetOrderByIdShouldReturnOKWhenOrderExists() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(200).
		when().
			get(ROUTE_ORDERS_ID, 1);
	}

	@Test
	public void testGetOrderByIdShouldReturnNotFoundWhenOrderDoesNotExist() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			statusCode(404).
		when().
			get(ROUTE_ORDERS_ID, 1000);
	}
	
	@Test
	public void testPutOrderByIdShouldReturnBadRequestAndDoNotUpdateOrderWhenOrderIdIsInvalid() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			statusCode(400).
		when().
			put(ROUTE_ORDERS_ID_STATUS, 1000, OrderStatus.CANCELED);
	}

	@Test
	public void testPutOrderByIdShouldReturnBadRequestAndDoNotUpdateOrderWhenStatusIsInvalid() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			statusCode(400).
		when().
			put(ROUTE_ORDERS_ID_STATUS, 1, "DUMMY");
	}

	@Test
	public void testPutOrderByIdShouldUpdateOrderAndReturnNoContentWhenOrderIdAndStatusAreValid() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			statusCode(204).
		when().
			put(ROUTE_ORDERS_ID_STATUS, 1, OrderStatus.CANCELED);
	}

	// TODO: Get all orders by table
}
