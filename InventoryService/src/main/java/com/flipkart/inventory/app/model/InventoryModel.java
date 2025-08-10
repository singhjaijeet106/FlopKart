package com.flipkart.inventory.app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryModel {
	private long id;
	private String inventoryId;
    private String productId;
    private Integer quantity;
	private List<InventoryHistoryModel> historyModels;
}
