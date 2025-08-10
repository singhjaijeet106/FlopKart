package com.flipkart.inventory.app.repository.repo;

import java.util.List;

import com.flipkart.inventory.app.repository.entity.Inventory;
import com.flipkart.inventory.app.repository.entity.InventoryHistory;

public interface InventorySystemRepository {
	Inventory getInventoryByProductId(String productId);
	Inventory addOrUpdateInventory(String productId, Integer quantity);
	Inventory increaseOrReduceStock(String productId, Integer quantity, boolean reduce);
	void saveHistory(InventoryHistory inventoryHistory, String orderId);
	List<InventoryHistory> getInventoryHistory(String productId);
}
