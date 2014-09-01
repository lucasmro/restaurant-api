package com.lucasmro.restaurant.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lucasmro.restaurant.dao.MongoDBManager;
import com.lucasmro.restaurant.dao.ProductDao;
import com.lucasmro.restaurant.dao.ProductDaoImpl;
import com.lucasmro.restaurant.exception.ResourceNotFoundException;
import com.lucasmro.restaurant.model.Product;

@Path("/products")
public class ProductController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() throws ResourceNotFoundException {
		// TODO: Change to Dependency Injection
		ProductDao productDao = new ProductDaoImpl(new MongoDBManager());

		List<Product> products = productDao.findAll();

		if (null == products) {
			throw new ResourceNotFoundException("No Product found");
		}

		return Response.status(Status.OK).entity(products).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("id") String id) throws ResourceNotFoundException {
		// TODO: Change to Dependency Injection
		ProductDao productDao = new ProductDaoImpl(new MongoDBManager());

		Product product = productDao.findById(id);

		if (null == product) {
			throw new ResourceNotFoundException("No Product found with Id " + id);
		}

		return Response.status(Status.OK).entity(product).build();
	}
}
