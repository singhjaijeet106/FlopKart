package com.flipkart.order.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.order.app.repository.entity.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long>{
	Optional<OrderDetails> findByOrderId(String orderId);
}
