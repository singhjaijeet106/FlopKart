package com.flipkart.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.order.app.repository.entity.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long>{

}
