package com.flipkart.inventory.app.service;

import com.flipkart.inventory.app.model.InventoryModel;

public interface InventoryService {
	InventoryModel getInventoryByProductId(String productId);
	InventoryModel addOrUpdateInventory(String productId, Integer quantity);
	InventoryModel increaseOrReduceStock(String productId, Integer quantity, boolean reduce);
}
