package com.lucasmro.restaurant.controller;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lucasmro.restaurant.dao.MongoDBManager;
import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.fixture.OrderFixture;
import com.lucasmro.restaurant.fixture.OrderFixtureHelper;
import com.lucasmro.restaurant.model.Order;

public class OrderControllerTest {
	private final static String ROUTE_ORDERS = "/orders/";
	private final static String ROUTE_ORDERS_ID = "/orders/{id}";
	private final static String ROUTE_ORDERS_ID_STATUS = "/orders/{id}/{status}";
	private final static String ROUTE_ORDERS_TABLE = "/orders/table/{table}";

	MongoDBManager manager;

	@Before
	public void setUp() {
		manager = new MongoDBManager();
		OrderFixtureHelper.load(manager);
	}

	@After
	public void tearDown() {
		OrderFixtureHelper.drop(this.manager);
	}

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

		// TODO: Create JsonConverter class to convert from Object to JSON and from JSON to Object.
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(order);

		given().
			contentType(MediaType.APPLICATION_JSON).
			body(json).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(400).
		when().
			post(ROUTE_ORDERS);
	}

	@Test
	public void testPostTableOrderShouldReturnCreatedWhenOrderIsValid() throws JsonGenerationException, JsonMappingException, IOException {
		OrderFixtureHelper.drop(this.manager);

		Order order = OrderFixture.createTableOrderHamburguer();

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
		OrderFixtureHelper.drop(this.manager);

		Order order = OrderFixture.createDeliveryOrder();

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
			get(ROUTE_ORDERS_ID, "5403d7f7c19e51e2ea3c02c3");
	}

	@Test
	public void testGetOrderByIdShouldReturnNotFoundWhenOrderDoesNotExist() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(404).
		when().
			get(ROUTE_ORDERS_ID, "444444444444444444444444");
	}

	@Test
	public void testGetAllOrdersByTableShouldReturnOKWhenTableHasOrders() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(200).
		when().
			get(ROUTE_ORDERS_TABLE, 5);
	}

	@Test
	public void testGetAllOrdersByTableShouldReturnNotFoundWhenTableDoesNotHaveOrders() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(404).
		when().
			get(ROUTE_ORDERS_TABLE, 1000);
	}

	@Test
	public void testPutOrderByIdShouldReturnNotFoundWhenOrderDoesNotExists() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(404).
		when().
			put(ROUTE_ORDERS_ID_STATUS, "444444444444444444444444", OrderStatus.CANCELED);
	}

	@Test
	public void testPutOrderByIdShouldReturnBadRequestAndDoNotUpdateOrderWhenStatusIsInvalid() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(400).
		when().
			put(ROUTE_ORDERS_ID_STATUS, "5403d7f7c19e51e2ea3c02c3", "DUMMY");
	}

	@Test
	public void testPutOrderByIdShouldUpdateOrderAndReturnNoContentWhenOrderIdAndStatusAreValid() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			statusCode(204).
		when().
			put(ROUTE_ORDERS_ID_STATUS, "5403d7f7c19e51e2ea3c02c3", OrderStatus.CANCELED);
	}
}
