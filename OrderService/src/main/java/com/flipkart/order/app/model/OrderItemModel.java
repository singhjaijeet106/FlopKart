package com.flipkart.order.app.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemModel {
	private String itemId;
	private int quantity;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
}
