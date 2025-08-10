package com.flipkart.inventory.app.repository.repo.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.flipkart.inventory.app.constant.InventoryAction;
import com.flipkart.inventory.app.exception.InventoryNotFoundException;
import com.flipkart.inventory.app.exception.InventoryValidationException;
import com.flipkart.inventory.app.helper.InventoryHelper;
import com.flipkart.inventory.app.repository.InventoryHistoryRepository;
import com.flipkart.inventory.app.repository.InventoryRepository;
import com.flipkart.inventory.app.repository.entity.Inventory;
import com.flipkart.inventory.app.repository.entity.InventoryHistory;
import com.flipkart.inventory.app.repository.repo.InventorySystemRepository;

@Repository
public class InventorySystemRepositoryImpl implements InventorySystemRepository {
	private final InventoryRepository inventoryRepository;
	private final InventoryHelper inventoryHelper;
	private final InventoryHistoryRepository inventoryHistoryRepository;

	public InventorySystemRepositoryImpl(InventoryRepository inventoryRepository, InventoryHelper inventoryHelper, InventoryHistoryRepository inventoryHistoryRepository ) {
		this.inventoryRepository = inventoryRepository;
		this.inventoryHelper = inventoryHelper;
		this.inventoryHistoryRepository = inventoryHistoryRepository;
	}

	@Override
	public Inventory getInventoryByProductId(String productId) {
		Inventory inventory = inventoryRepository.findByProductId(productId).orElse(null);
		if(inventory!=null) {
			List<InventoryHistory> inventoryHistories = inventoryHistoryRepository.findByProductIdOrderByTimestampDesc(productId);
			inventory.setHistory(inventoryHistories);
		}
		return inventoryRepository.findByProductId(productId).orElse(null);
	}

	@Override
	public Inventory addOrUpdateInventory(String productId, Integer quantity) {
		Inventory inventory = inventoryRepository.findByProductId(productId).orElse(null);
		InventoryHistory inventoryHistory = InventoryHistory.builder()
				.finalQuantity(quantity)
				.productId(productId)
				.action(InventoryAction.ADJUST)
				.build();
		
		if (inventory == null) {
			inventory = Inventory.builder().productId(productId).inventoryId(inventoryHelper.generateUniqueInventoryId()).quantity(quantity)
					.build();
			inventoryHistory.setAction(InventoryAction.ADD);
			inventoryHistory.setQuantityChange(quantity);
		}else {
			inventoryHistory.setQuantityChange(inventory.getQuantity()- quantity);
			inventory.setQuantity(quantity);
		}
		
		inventory =  inventoryRepository.save(inventory);
		
		inventoryHistory.setInventory(inventory);
		
		
		saveHistory(inventoryHistory,"ORDER_ID");
		
		return inventory;
	}

	@Override
	public Inventory increaseOrReduceStock(String productId, Integer quantity, boolean reduce) {
		Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Product not found in inventory"));
        if (inventory.getQuantity() < quantity) {
            throw new InventoryValidationException("Not enough stock");
        }
        inventory.setQuantity( (reduce)?inventory.getQuantity() - quantity:inventory.getQuantity() + quantity );
        inventory = inventoryRepository.save(inventory);
        
        InventoryHistory inventoryHistory = InventoryHistory.builder()
       		 .productId(productId)
                .quantityChange(quantity)
                .finalQuantity(inventory.getQuantity())
                .action((reduce)? InventoryAction.REDUCE : InventoryAction.ADD)
                .inventory(inventory)
                .build();
        saveHistory(inventoryHistory, productId);
        return inventory;
	}
	
	
	@Override
	public void saveHistory(InventoryHistory inventoryHistory, String orderId) {
		inventoryHistory.setTimestamp(LocalDateTime.now());
		if(inventoryHistory.getQuantityChange()<0) {
			inventoryHistory.setQuantityChange(inventoryHistory.getQuantityChange()*-1);
		}
		inventoryHistoryRepository.save(inventoryHistory);
	}

	@Override
	public List<InventoryHistory> getInventoryHistory(String productId) {
		List<InventoryHistory> history = inventoryHistoryRepository.findByProductIdOrderByTimestampDesc(productId);
		return history;
	}

}
