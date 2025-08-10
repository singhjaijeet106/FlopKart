package com.flipkart.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.order.app.repository.entity.OrderItem;

public interface OrderDetailItemsRepository extends JpaRepository<OrderItem, Long>{

}
