package com.flipkart.inventory.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.inventory.app.repository.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	Optional<Inventory> findByProductId(String productId);
}
