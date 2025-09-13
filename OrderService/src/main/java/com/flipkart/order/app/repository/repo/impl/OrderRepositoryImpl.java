package com.flipkart.order.app.repository.repo.impl;

import org.springframework.stereotype.Repository;

import com.flipkart.order.app.model.OrderDetailsModel;
import com.flipkart.order.app.model.OrderItemModel;
import com.flipkart.order.app.repository.OrderDetailRepository;
import com.flipkart.order.app.repository.entity.OrderDetails;
import com.flipkart.order.app.repository.repo.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
	
	private OrderDetailRepository orderDetailRepository;
	
	public OrderRepositoryImpl(OrderDetailRepository orderDetailRepository) {
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public OrderDetailsModel addToCart(OrderItemModel orderItemModel, String orderId) {
		
		OrderDetails orderDetails = null;
		if(orderId==null) {
			orderDetails = new OrderDetails();
		}else {
			orderDetails = orderDetailRepository.findByOrderId(orderId).orElse(null);
		}
		
		
		
		
		return null;
	}

}
