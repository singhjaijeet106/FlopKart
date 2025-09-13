package com.flipkart.order.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.flipkart.order.app.constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsModel {
	private String orderId;
	private LocalDateTime orderDate;
	private BigDecimal totalAmount;
	private String shippingAddress;
	private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemModel> items;
}
