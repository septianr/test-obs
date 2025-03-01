package com.obs.testobs.repository;

import com.obs.testobs.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, String> {
}
