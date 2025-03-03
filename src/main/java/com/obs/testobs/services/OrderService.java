package com.obs.testobs.services;

import com.obs.testobs.DTO.OrderRequestDTO;
import com.obs.testobs.model.InventoryModel;
import com.obs.testobs.model.ItemModel;
import com.obs.testobs.model.OrderModel;
import com.obs.testobs.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private InventoryService inventoryService;

    public OrderModel getOrderById(int id) throws Exception {
        Optional<OrderModel> getOrder = orderRepository.findById(Long.valueOf(id));
        if(!getOrder.isPresent() || getOrder.get().isStatus() == false) {
            throw new Exception("Order Not Found");
        }
        log.info("result {}", getOrder.get());
        return getOrder.get();
    }

    public Page<OrderModel> getOrderAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByStatus(true, pageable);
    }

    @Transactional
    public void createOrder(OrderRequestDTO request) throws Exception {
        OrderModel order = new OrderModel();
        ItemModel getItemById = itemService.getItemById(request.getItemId());
        order.setTimeCreated(LocalDateTime.now());
        order.setTimeUpdated(LocalDateTime.now());
        order.setOrderNo(generateOrderNo());
        order.setPrice(request.getPrice());
        order.setQty(request.getQty());
        order.setItemId(getItemById);
        order.setStatus(true);
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(OrderRequestDTO request) throws Exception {
        ItemModel getItemById = itemService.getItemById(request.getItemId());
        InventoryModel getInventory = inventoryService.getItemById(request.getInventoryId());
        if(request.getQty() > getInventory.getQty()) {
            throw new Exception("Stock Inventory Insufficient");
        }
        Optional<OrderModel> cekOrder = orderRepository.findById(Long.valueOf(request.getOrderId()));
        if(!cekOrder.isPresent() || cekOrder.get().isStatus() == false) {
            throw new Exception("Order Not Found");
        }

        OrderModel order = cekOrder.get();
        order.setTimeUpdated(LocalDateTime.now());
        order.setQty(request.getQty());


    }

    @Transactional
    public void softDeleteOrder(OrderRequestDTO request) throws Exception {

        Optional<OrderModel> cekOrder = orderRepository.findById(Long.valueOf(request.getOrderId()));
        if(!cekOrder.isPresent() || cekOrder.get().isStatus() == false){
            throw new Exception("Data Not Found");
        }
        OrderModel order = cekOrder.get();
        order.setTimeUpdated(LocalDateTime.now());
        order.setStatus(false);
        orderRepository.save(order);
    }


    private String generateOrderNo() {
        int firstIncrement = 1;
        String orderNo = "";
        String cekOrder = orderRepository.findByOrderNo();
        if(!cekOrder.isEmpty() || !cekOrder.isBlank()) {
            int lastNoOrder = Integer.parseInt(cekOrder.substring(1));
            int newNumber = lastNoOrder + 1;
            orderNo = "o" + newNumber;
        } else {
            orderNo = "O" + firstIncrement++;
        }
        return orderNo;
    }



}
