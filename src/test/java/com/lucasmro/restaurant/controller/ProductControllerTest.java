package com.lucasmro.restaurant.controller;

import static com.jayway.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lucasmro.restaurant.dao.MongoDBManager;
import com.lucasmro.restaurant.fixture.ProductFixtureHelper;

public class ProductControllerTest {
	private final static String ROUTE_PRODUCTS = "/products/";
	private final static String ROUTE_PRODUCTS_ID = "/products/{id}";

	MongoDBManager manager;

	@Before
	public void setUp() {
		manager = new MongoDBManager();
		ProductFixtureHelper.load(manager);
	}

	@After
	public void tearDown() {
		ProductFixtureHelper.drop(this.manager);
	}

	@Test
	public void testGetProductByIdShouldReturnOKWhenProductExists() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).	
			statusCode(200).
		when().
			get(ROUTE_PRODUCTS_ID, "5403d655c19e51e2ea3c02c0");
	}

	@Test
	public void testGetProductByIdShouldReturnNotFoundWhenProductDoesNotExist() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(404).
		when().
			get(ROUTE_PRODUCTS_ID, "555555555555555555555555");
	}

	@Test
	public void testGetProductsShouldReturnOKWhenProductsExists() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(200).
		when().
			get(ROUTE_PRODUCTS);
	}

	@Test
	public void testGetProductsShouldReturnNotFoundWhenProductsDoNotExist() {
		ProductFixtureHelper.drop(this.manager);

		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(404).
		when().
			get(ROUTE_PRODUCTS);
	}
}
