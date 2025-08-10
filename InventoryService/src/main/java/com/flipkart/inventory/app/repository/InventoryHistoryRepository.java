package com.flipkart.inventory.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flipkart.inventory.app.repository.entity.InventoryHistory;

public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Long>{
	List<InventoryHistory> findByProductIdOrderByTimestampDesc(String productId);
}
