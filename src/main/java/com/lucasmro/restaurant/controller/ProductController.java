package com.lucasmro.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lucasmro.restaurant.exception.ResourceNotFoundException;
import com.lucasmro.restaurant.fixture.ProductFixture;
import com.lucasmro.restaurant.model.Product;

@Path("/products")
public class ProductController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		// TODO: Create persistence layer

		Product product1 = ProductFixture.createXEggHamburguer();
		Product product2 = ProductFixture.createCocaColaDrink();

		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);

		return Response.status(Status.OK).entity(products).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("id") Integer id) throws ResourceNotFoundException {
		// TODO: Create persistence layer
		Product product = null;

		if (id == 1) {
			product = ProductFixture.createXEggHamburguer();
		}

		if (null == product) {
			throw new ResourceNotFoundException("No Product found with Id " + id);
		}

		return Response.status(Status.OK).entity(product).build();
	}
}
