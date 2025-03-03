package com.obs.testobs.repository;

import com.obs.testobs.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    Page<OrderModel> findByStatus(boolean status, Pageable pageable);

    @Query(value = "select o.orderNo from OrderModel o order by o.id desc limit 1")
    String findByOrderNo();
}
