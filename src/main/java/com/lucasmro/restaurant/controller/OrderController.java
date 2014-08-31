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

import org.apache.commons.lang3.EnumUtils;

import com.lucasmro.restaurant.enums.OrderStatus;
import com.lucasmro.restaurant.enums.ProductType;
import com.lucasmro.restaurant.fixture.OrderFixture;
import com.lucasmro.restaurant.model.Order;
import com.lucasmro.restaurant.model.OrderItem;
import com.lucasmro.restaurant.model.Product;

@Path("/orders")
public class OrderController {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveOrder(Order order) {
		if (!isValid(order)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		// TODO: Create persistence layer

		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("id") Integer id) {
		// TODO: Create persistence layer

		if (id == 1) {
			Product product = new Product();
			product.setId(1);
			product.setType(ProductType.HAMBURGUER);
			product.setDescription("X-EGG");
			product.setPrice(10.5);

			OrderItem item = new OrderItem();
			item.setProduct(product);
			item.setQuantity(2);

			Order order = new Order();
			order.setId(1);
			order.setStatus(OrderStatus.WAITING);
			order.addItem(item);
			order.setTotal(item.getProduct().getPrice() * item.getQuantity());

			return Response.status(200).entity(order).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/table/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdersByTable(@PathParam("table") Integer table) {
		// TODO: Create persistence layer

		if (table == 5) {
			Order order1 = OrderFixture.createTableOrderHamburguer();
			Order order2 = OrderFixture.createTableOrderDrink();

			List<Order> orders = new ArrayList<Order>();
			orders.add(order1);
			orders.add(order2);

			return Response.status(200).entity(orders).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("/{id}/{status}")
	public Response putOrderStatusByOrderId(@PathParam("id") Integer id, @PathParam("status") String status) {
		// TODO: Create persistence layer and change the following validation

		if (id == 1000 || !EnumUtils.isValidEnum(OrderStatus.class, status)) {
			// TODO: Maybe return a JSON error response ?!?
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// TODO: Extract method to a validator class
	private boolean isValid(Order order) {
		return (null != order.getItems() && order.getItems().size() > 0) &&
			   (order.getTotal() > 0.0) &&
			   !(null != order.getTable() && null != order.getDelivery()) && // Should return false when table number and delivery are both set.
			   (null != order.getTable() || null != order.getDelivery());
		// TODO: Validate address
		// TODO: Validate delivery
	}
}
