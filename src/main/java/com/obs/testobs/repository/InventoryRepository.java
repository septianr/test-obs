package com.obs.testobs.repository;

import com.obs.testobs.model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {
}
