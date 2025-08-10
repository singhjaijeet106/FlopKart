package com.flipkart.inventory.app.helper;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.flipkart.inventory.app.model.InventoryHistoryModel;
import com.flipkart.inventory.app.model.InventoryModel;
import com.flipkart.inventory.app.repository.entity.Inventory;
import com.flipkart.inventory.app.repository.entity.InventoryHistory;

@Component
public class InventoryHelper {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 20;
    private static final SecureRandom random = new SecureRandom();
	
	
	public InventoryModel convertToInventoryModel(Inventory inventory) {
		if (inventory == null)
			return null;
		return InventoryModel.builder().id(inventory.getId()).inventoryId(inventory.getInventoryId())
				.productId(inventory.getProductId()).quantity(inventory.getQuantity()).build();
	}

	public Inventory convertToInventoryEntity(InventoryModel model) {
		if (model == null)
			return null;
		return Inventory.builder().id(model.getId()).inventoryId(model.getInventoryId()).productId(model.getProductId())
				.quantity(model.getQuantity()).build();
	}

	public InventoryHistoryModel convertToInventoryHistoryModel(InventoryHistory inventoryHistory) {
		if (inventoryHistory == null)
			return null;
		return InventoryHistoryModel.builder().id(inventoryHistory.getId()).productId(inventoryHistory.getProductId())
				.quantityChange(inventoryHistory.getQuantityChange()).finalQuantity(inventoryHistory.getFinalQuantity())
				.action(inventoryHistory.getAction()).orderId(inventoryHistory.getOrderId())
				.timestamp(inventoryHistory.getTimestamp()).inventory(inventoryHistory.getInventory()).build();
	}

	public InventoryHistory convertToInventoryHistoryEntity(InventoryHistoryModel model) {
		if (model == null)
			return null;
		return InventoryHistory.builder().id(model.getId()).productId(model.getProductId())
				.quantityChange(model.getQuantityChange()).finalQuantity(model.getFinalQuantity())
				.action(model.getAction()).orderId(model.getOrderId()).timestamp(model.getTimestamp())
				.inventory(model.getInventory()).build();
	}
	
	 public String generateUniqueInventoryId() {
	        StringBuilder sb = new StringBuilder(ID_LENGTH);
	        for (int i = 0; i < ID_LENGTH; i++) {
	            int index = random.nextInt(CHARACTERS.length());
	            sb.append(CHARACTERS.charAt(index));
	        }
	        return sb.toString();
	    }
}
