package com.obs.testobs.repository;

import com.obs.testobs.model.ItemModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {

    @Query(value = "select i from ItemModel i where i.name LIKE %:name%")
    public List<ItemModel> findByName(@Param("name") String name);

    Page<ItemModel> findByStatus(boolean status, Pageable pageable);
}
