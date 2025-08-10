package com.flipkart.inventory.app.model;

import java.time.LocalDateTime;

import com.flipkart.inventory.app.constant.InventoryAction;
import com.flipkart.inventory.app.repository.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryHistoryModel {
private Long id;
    
    private String productId;
    private Integer quantityChange; // Positive for add, negative for reduce
    private Integer finalQuantity;
    private InventoryAction action; // e.g. "ADD", "REDUCE", "ADJUST"
    
    private String orderId; // New field to link stock change to an order
    private LocalDateTime timestamp;
    private String inventory;
}
