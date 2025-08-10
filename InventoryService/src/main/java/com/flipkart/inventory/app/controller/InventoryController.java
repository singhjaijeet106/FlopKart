package com.flipkart.inventory.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.inventory.app.model.InventoryHistoryModel;
import com.flipkart.inventory.app.model.InventoryModel;
import com.flipkart.inventory.app.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryModel> getInventory(@PathVariable String productId) {
        InventoryModel inventory = inventoryService.getInventoryByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @PostMapping
    public ResponseEntity<InventoryModel> addOrUpdateInventory(@RequestParam String productId, @RequestParam Integer quantity) {
        InventoryModel inventory = inventoryService.addOrUpdateInventory(productId, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }

    @PutMapping("/{productId}/reduce")
    public ResponseEntity<InventoryModel> reduceStock(@PathVariable String productId, @RequestParam Integer quantity) {
    	 InventoryModel inventory = inventoryService.increaseOrReduceStock(productId, quantity,true);
    	 return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }
    
    @PutMapping("/{productId}/add")
    public ResponseEntity<InventoryModel> increaseStock(@PathVariable String productId, @RequestParam Integer quantity) {
    	InventoryModel inventory = inventoryService.increaseOrReduceStock(productId, quantity,false);
    	 return ResponseEntity.status(HttpStatus.OK).body(inventory);
    }
    
    @GetMapping("/{productId}/history")
    public ResponseEntity<List<InventoryHistoryModel>> getInventoryHistory(@PathVariable String productId) {
        List<InventoryHistoryModel> history = inventoryService.getInventoryHistory(productId);
        return ResponseEntity.ok(history);
    }
}