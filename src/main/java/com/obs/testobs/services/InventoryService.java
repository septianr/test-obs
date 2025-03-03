package com.obs.testobs.services;

import com.obs.testobs.DTO.InventoryRequestDTO;
import com.obs.testobs.DTO.ItemRequestDTO;
import com.obs.testobs.constant.InventoryConstant;
import com.obs.testobs.model.InventoryModel;
import com.obs.testobs.model.ItemModel;
import com.obs.testobs.repository.InventoryRepository;
import com.obs.testobs.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemService itemService;

    public InventoryModel getItemById(int id) throws Exception {

        Optional<InventoryModel> result = inventoryRepository.findById(Long.valueOf(id));
        if(!result.isPresent() || result.get().isStatus() == false){
            throw new Exception("Data Not Found");
        }
        log.info("result {}", result);
        return result.get();
    }

    public Page<InventoryModel> getInventoryAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return inventoryRepository.findByStatus(true, pageable);
    }

    @Transactional
    public void createInventory(InventoryRequestDTO request) throws Exception {
        InventoryModel inventory = new InventoryModel();
        ItemModel getItemById = itemService.getItemById(request.getItemId());
        inventory.setTimeCreated(LocalDateTime.now());
        inventory.setTimeUpdated(LocalDateTime.now());
        inventory.setType(InventoryConstant.topUp);
        inventory.setItemId(getItemById);
        inventory.setQty(request.getQty());
        inventory.setStatus(true);
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void updateInventory(InventoryRequestDTO request) throws Exception {

        Optional<InventoryModel> inventoryCek = inventoryRepository.findById(Long.valueOf(request.getId()));
        if(!inventoryCek.isPresent() || inventoryCek.get().isStatus() == false){
            throw new Exception("Data Not Found");
        }
        InventoryModel inventory = inventoryCek.get();
        inventory.setTimeUpdated(LocalDateTime.now());
        if(request.getType().equals(InventoryConstant.topUp)){
            int qtyExisting = inventory.getQty();
            int qtyNew = inventory.getQty() + request.getQty();
            inventory.setQty(qtyNew);
        } else if (request.getType().equals(InventoryConstant.withdrawal)) {
            int qtyExisting = inventory.getQty();
            int qtyNew = inventory.getQty() - request.getQty();
            inventory.setQty(qtyNew);
        }
        inventory.setType(request.getType());
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void softDeleteInventory(InventoryRequestDTO request) throws Exception {

        Optional<InventoryModel> cekInventory = inventoryRepository.findById(Long.valueOf(request.getId()));
        if(!cekInventory.isPresent() || cekInventory.get().isStatus() == false){
            throw new Exception("Data Not Found");
        }
        InventoryModel inventory = cekInventory.get();
        inventory.setQty(0);
        inventory.setType(InventoryConstant.withdrawal);
        inventory.setTimeUpdated(LocalDateTime.now());
        inventory.setStatus(false);
        inventoryRepository.save(inventory);
    }

}
