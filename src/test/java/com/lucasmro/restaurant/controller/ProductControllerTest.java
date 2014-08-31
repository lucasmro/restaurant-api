package com.lucasmro.restaurant.controller;

import static com.jayway.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

public class ProductControllerTest {
	private final static String ROUTE_PRODUCTS = "/products/";
	private final static String ROUTE_PRODUCTS_ID = "/products/{id}";

	@Test
	public void testGetProductByIdShouldReturnOKWhenProductExists() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).	
			statusCode(200).
		when().
			get(ROUTE_PRODUCTS_ID, 1);
	}

	@Test
	public void testGetProductByIdShouldReturnNotFoundWhenProductDoesNotExist() {
		given().
			contentType(MediaType.APPLICATION_JSON).
		expect().
			contentType(MediaType.APPLICATION_JSON).
			statusCode(404).
		when().
			get(ROUTE_PRODUCTS_ID, 1000);
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
}
