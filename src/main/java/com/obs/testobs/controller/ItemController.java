package com.obs.testobs.controller;

import com.obs.testobs.DTO.ItemRequestDTO;
import com.obs.testobs.DTO.ResponseApi;
import com.obs.testobs.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllItems(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(new ResponseApi(itemService.getItemAll(page, size)));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getItemById(@RequestParam int id) {
        try{
            return ResponseEntity.ok(new ResponseApi(itemService.getItemById(id)));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed get Data"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getItemlikeByName(@RequestParam String name) {
        try{
            return ResponseEntity.ok(new ResponseApi(itemService.searchItemByName(name)));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed get Data"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createData(@RequestBody ItemRequestDTO request) {
        try{
            return ResponseEntity.ok(new ResponseApi("success", request));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Create Data"+" name : " +request.getName()+ " price : " +request.getPrice()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateData(@RequestBody ItemRequestDTO request) {
        try{
            return ResponseEntity.ok(new ResponseApi("success", 00));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Create Data"+" name : " +request.getName()));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> softDeleteItem(@RequestBody ItemRequestDTO request) {
        try{
            return ResponseEntity.ok(new ResponseApi("success", 00, "Data Deleted"));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Create Data"+" name : " +request.getName()));
        }
    }
}
