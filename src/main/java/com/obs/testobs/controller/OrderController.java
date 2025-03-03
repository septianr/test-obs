package com.obs.testobs.controller;

import com.obs.testobs.DTO.InventoryRequestDTO;
import com.obs.testobs.DTO.OrderRequestDTO;
import com.obs.testobs.DTO.ResponseApi;
import com.obs.testobs.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllItems(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(new ResponseApi(orderService.getOrderAll(page, size)));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getItemById(@RequestParam int id) {
        try{
            return ResponseEntity.ok(new ResponseApi(orderService.getOrderById(id)));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed get Data"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createData(@RequestBody OrderRequestDTO request) {
        try{
            orderService.createOrder(request);
            return ResponseEntity.ok(new ResponseApi("success", request));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Create Data Order"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateData(@RequestBody OrderRequestDTO request) {
        try{
            orderService.updateOrder(request);
            return ResponseEntity.ok(new ResponseApi("success", 00));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Update Data Order"));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> softDeleteItem(@RequestBody OrderRequestDTO request) {
        try{
            orderService.softDeleteOrder(request);
            return ResponseEntity.ok(new ResponseApi("success", 00, "Data Deleted"));
        } catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ResponseApi("Failed",
                    "Failed Deleted Data Order"));
        }
    }
}
