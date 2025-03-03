package com.obs.testobs.controller;

import com.obs.testobs.DTO.InventoryRequestDTO;
import com.obs.testobs.DTO.ResponseApi;
import com.obs.testobs.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllItems(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(new ResponseApi(inventoryService.getInventoryAll(page, size)));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getItemById(@RequestParam int id) {
        try{
            return ResponseEntity.ok(new ResponseApi(inventoryService.getItemById(id)));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed get Data"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createData(@RequestBody InventoryRequestDTO request) {
        try{
            inventoryService.createInventory(request);
            return ResponseEntity.ok(new ResponseApi("success", 00));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Create Data Inventory"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateData(@RequestBody InventoryRequestDTO request) {
        try{
            inventoryService.updateInventory(request);
            return ResponseEntity.ok(new ResponseApi("success", 00));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Update Data Inventory"));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> softDeleteItem(@RequestBody InventoryRequestDTO request) {
        try{
            inventoryService.softDeleteInventory(request);
            return ResponseEntity.ok(new ResponseApi("success", 00, "Data Deleted"));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Deleted Data Inventory"));
        }
    }

}
