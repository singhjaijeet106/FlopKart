package com.flipkart.order.app.repository.repo;

import com.flipkart.order.app.model.OrderDetailsModel;
import com.flipkart.order.app.model.OrderItemModel;

public interface OrderRepository {

	OrderDetailsModel addToCart(OrderItemModel orderItemModel,String orderId);
}
