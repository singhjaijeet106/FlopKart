package com.flipkart.order.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.order.app.model.OrderDetailsModel;
import com.flipkart.order.app.model.request.AddToCartRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@PostMapping("/cart/add")
	public ResponseEntity<OrderDetailsModel> postMethodName(@RequestBody AddToCartRequest cartItem) {
		OrderDetailsModel orderDetailsModel = null;
		if (cartItem.getOrderId() == null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailsModel);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(orderDetailsModel);
		}
	}

	@PutMapping("/{orderId}/info")
	public ResponseEntity<OrderDetailsModel> updateOrderInformation(@RequestBody OrderDetailsModel orderDetails,
			@PathVariable String orderId) {
		return ResponseEntity.status(HttpStatus.OK).body(orderDetails);
	}

	@PostMapping("/{orderId}/checkout")
	public ResponseEntity<OrderDetailsModel> checkout(@PathVariable String orderId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@PostMapping("/{orderId}/cancel")
	public ResponseEntity<OrderDetailsModel> cancelOrder(@PathVariable String orderId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDetailsModel> getOrder(@PathVariable String orderId) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping
	public ResponseEntity<List<OrderDetailsModel>> getAllOrders() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping
	public ResponseEntity<List<OrderDetailsModel>> getAllOrdersByUser() {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
