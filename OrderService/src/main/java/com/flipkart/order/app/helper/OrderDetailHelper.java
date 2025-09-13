package com.flipkart.order.app.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flipkart.order.app.model.OrderDetailsModel;
import com.flipkart.order.app.model.OrderItemModel;
import com.flipkart.order.app.repository.entity.OrderDetails;
import com.flipkart.order.app.repository.entity.OrderItem;

@Component
public class OrderDetailHelper {
	public OrderItemModel convertFromEntity(OrderItem orderItem) {
		return OrderItemModel.builder()
				.itemId(orderItem.getItemId())
				.quantity(orderItem.getQuantity())
				.unitPrice(orderItem.getUnitPrice())
				.totalPrice(orderItem.getTotalPrice())
				.build();				
	}
	
	public OrderItem convertFromModel(OrderItemModel orderItem) {
		return OrderItem.builder()
				.itemId(orderItem.getItemId())
				.quantity(orderItem.getQuantity())
				.unitPrice(orderItem.getUnitPrice())
				.totalPrice(orderItem.getTotalPrice())
				.build();				
	}
	public OrderDetailsModel convertFromEntity(OrderDetails orderDetails) {
		OrderDetailsModel orderDetailsModel = OrderDetailsModel.builder()
				.orderId(orderDetails.getOrderId())
				.orderDate(orderDetails.getOrderDate())
				.totalAmount(orderDetails.getTotalAmount())
				.shippingAddress(orderDetails.getShippingAddress())
				.status(orderDetails.getStatus())
				.createdAt(orderDetails.getCreatedAt())
				.updatedAt(orderDetails.getUpdatedAt())
				.build();
		
		if(orderDetails.getItems()!=null && !orderDetails.getItems().isEmpty()) {
			List<OrderItemModel> itemList  = orderDetails.getItems().stream().map(item->this.convertFromEntity(item)).toList();
			orderDetailsModel.setItems(itemList);
		}
		
		return orderDetailsModel;
	}
	
	public OrderDetails convertFromModel(OrderDetailsModel orderDetails) {
		OrderDetails order  = OrderDetails.builder()
				.orderId(orderDetails.getOrderId())
				.orderDate(orderDetails.getOrderDate())
				.totalAmount(orderDetails.getTotalAmount())
				.shippingAddress(orderDetails.getShippingAddress())
				.status(orderDetails.getStatus())
				.createdAt(orderDetails.getCreatedAt())
				.updatedAt(orderDetails.getUpdatedAt())				
				.build();
		
		if(orderDetails.getItems()!=null && !orderDetails.getItems().isEmpty()) {
			List<OrderItem> itemList  = orderDetails.getItems().stream().map(item->this.convertFromModel(item)).toList();
			order.setItems(itemList);
		}
		return order;
	}
}
