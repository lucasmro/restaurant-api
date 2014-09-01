package com.lucasmro.restaurant.controller;

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

import com.lucasmro.restaurant.dao.MongoDBManager;
import com.lucasmro.restaurant.dao.OrderDao;
import com.lucasmro.restaurant.dao.OrderDaoImpl;
import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.exception.ApplicationException;
import com.lucasmro.restaurant.exception.ResourceNotFoundException;
import com.lucasmro.restaurant.model.Order;

@Path("/orders")
public class OrderController {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveOrder(Order order) throws ApplicationException {
		if (!order.isValid()) {
			throw new ApplicationException("Order is invalid!");
		}

		// TODO: Change to Dependency Injection
		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());
		orderDao.persist(order);

		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("id") String id) throws ResourceNotFoundException {
		// TODO: Change to Dependency Injection
		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());

		Order order = orderDao.findById(id);

		if (null == order) {
			throw new ResourceNotFoundException("No Order found with Id " + id);
		}

		return Response.status(Status.OK).entity(order).build();
	}

	@GET
	@Path("/table/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdersByTable(@PathParam("table") Integer table) throws ResourceNotFoundException {
		// TODO: Change to Dependency Injection
		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());

		List<Order> orders = orderDao.findAllByTable(table);

		if (null == orders) {
			throw new ResourceNotFoundException("No Orders found for table " + table);
		}

		return Response.status(Status.OK).entity(orders).build();
	}

	@PUT
	@Path("/{id}/{status}")
	public Response putOrderStatusByOrderId(@PathParam("id") String id, @PathParam("status") String status) throws ResourceNotFoundException, ApplicationException {
		// TODO: Change to Dependency Injection
		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());

		Order order = orderDao.findById(id);

		if (null == order) {
			throw new ResourceNotFoundException("No Order found with Id " + id);
		}

		if (!EnumUtils.isValidEnum(OrderStatus.class, status)) {
			throw new ApplicationException("Status is invalid!");
		}

		return Response.status(Status.NO_CONTENT).build();
	}
}
