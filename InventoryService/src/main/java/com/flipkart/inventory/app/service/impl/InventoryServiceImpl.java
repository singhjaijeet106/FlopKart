package com.flipkart.inventory.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flipkart.inventory.app.exception.InventoryValidationException;
import com.flipkart.inventory.app.helper.InventoryHelper;
import com.flipkart.inventory.app.model.InventoryModel;
import com.flipkart.inventory.app.repository.entity.Inventory;
import com.flipkart.inventory.app.repository.repo.InventorySystemRepository;
import com.flipkart.inventory.app.service.InventoryService;



@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventorySystemRepository inventoryRepository;
	private final InventoryHelper inventoryHelper;
	
	
	public InventoryServiceImpl(InventorySystemRepository inventoryRepository,InventoryHelper inventoryHelper) {
		this.inventoryRepository = inventoryRepository;
		this.inventoryHelper = inventoryHelper;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public InventoryModel getInventoryByProductId(String productId) {
		if(productId==null || productId.isEmpty()) {
			throw new InventoryValidationException("product id is required" );
		}
		Inventory inventory = inventoryRepository.getInventoryByProductId(productId);
		
		return inventoryHelper.convertToInventoryModel(inventory);
	}

	@Override
	@Transactional
	public InventoryModel addOrUpdateInventory(String productId, Integer quantity) {
		if(productId==null || productId.isEmpty()) {
			throw new InventoryValidationException("product id is required" );
		}
		if(quantity==null || quantity<=0) {
			throw new InventoryValidationException("Quantiy should be greater than 0" );
		}
		Inventory inventory = inventoryRepository.addOrUpdateInventory(productId, quantity);
		return inventoryHelper.convertToInventoryModel(inventory);
	}

	@Override
	public InventoryModel increaseOrReduceStock(String productId, Integer quantity, boolean reduce) {
		if(productId==null || productId.isEmpty()) {
			throw new InventoryValidationException("product id is required" );
		}
		if(quantity==null || quantity<=0) {
			throw new InventoryValidationException("Quantiy should be greater than 0" );
		}
		Inventory inventory = inventoryRepository.increaseOrReduceStock(productId, quantity,reduce);
		return inventoryHelper.convertToInventoryModel(inventory);
	}

}
