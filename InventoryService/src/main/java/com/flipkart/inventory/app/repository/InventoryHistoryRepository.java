package com.flipkart.inventory.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flipkart.inventory.app.repository.entity.InventoryHistory;

public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Long>{

}
