package com.lucasmro.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.EnumUtils;

import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.enums.ProductType;
import com.lucasmro.restaurant.exception.ApplicationException;
import com.lucasmro.restaurant.exception.ResourceNotFoundException;
import com.lucasmro.restaurant.fixture.OrderFixture;
import com.lucasmro.restaurant.model.Order;
import com.lucasmro.restaurant.model.OrderItem;
import com.lucasmro.restaurant.model.Product;

@Path("/orders")
public class OrderController {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveOrder(Order order) throws ApplicationException {
		if (!order.isValid()) {
			throw new ApplicationException("Order is invalid!");
		}

		// TODO: Create persistence layer

		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("id") Integer id) throws ResourceNotFoundException {
		// TODO: Create persistence layer
		Order order = null;

		if (id == 1) {
			Product product = new Product();
			product.setId(1);
			product.setType(ProductType.HAMBURGUER);
			product.setDescription("X-EGG");
			product.setPrice(10.5);

			OrderItem item = new OrderItem();
			item.setProduct(product);
			item.setQuantity(2);

			order = new Order();
			order.setId(1);
			order.setStatus(OrderStatus.WAITING);
			order.addItem(item);
			order.setTotal(item.getProduct().getPrice() * item.getQuantity());
		}

		if (null == order) {
			throw new ResourceNotFoundException("No Order found with Id " + id);
		}

		return Response.status(Status.OK).entity(order).build();
	}

	@GET
	@Path("/table/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdersByTable(@PathParam("table") Integer table) throws ResourceNotFoundException {
		// TODO: Create persistence layer
		List<Order> orders = null;

		if (table == 5) {
			Order order1 = OrderFixture.createTableOrderHamburguer();
			Order order2 = OrderFixture.createTableOrderDrink();

			orders = new ArrayList<Order>();
			orders.add(order1);
			orders.add(order2);
		}

		if (null == orders) {
			throw new ResourceNotFoundException("No Orders found for table " + table);
		}

		return Response.status(Status.OK).entity(orders).build();
	}

	@PUT
	@Path("/{id}/{status}")
	public Response putOrderStatusByOrderId(@PathParam("id") Integer id, @PathParam("status") String status) throws ResourceNotFoundException, ApplicationException {
		// TODO: Create persistence layer

		if (id == 1000) {
			throw new ResourceNotFoundException("No Order found with Id " + id);
		}

		if (!EnumUtils.isValidEnum(OrderStatus.class, status)) {
			throw new ApplicationException("Status is invalid!");
		}

		return Response.status(Status.NO_CONTENT).build();
	}
}
