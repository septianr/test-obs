package com.obs.testobs.services;

import com.obs.testobs.DTO.ItemRequestDTO;
import com.obs.testobs.model.ItemModel;
import com.obs.testobs.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemModel getItemById(int id) throws Exception {

        Optional<ItemModel> result = itemRepository.findById(Long.valueOf(id));
        if(!result.isPresent()){
            throw new Exception("Data Not Found");
        }
        log.info("result {}", result);
        return result.get();
    }

    public List<ItemModel> searchItemByName(String name){
        List<ItemModel> result = itemRepository.findByName(name);
        return result;
    }

    public Page<ItemModel> getItemAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return itemRepository.findByStatus(true, pageable);
    }

    public void createItem(ItemRequestDTO request) {
        ItemModel item = new ItemModel();
        item.setTimeCreated(LocalDateTime.now());
        item.setTimeUpdated(LocalDateTime.now());
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        itemRepository.save(item);
    }

    public void updateItem(ItemRequestDTO request) throws Exception {

        Optional<ItemModel> item = itemRepository.findById(Long.valueOf(request.getId()));
        if(!item.isPresent() || item.get().isStatus() == false){
            throw new Exception("Data Not Found");
        }
        ItemModel itemModel = item.get();
        itemModel.setTimeUpdated(LocalDateTime.now());
        itemModel.setName(request.getName());
        itemModel.setPrice(request.getPrice());
        itemRepository.save(itemModel);
    }

    public void softDeleteItem(ItemRequestDTO request) throws Exception {

        Optional<ItemModel> item = itemRepository.findById(Long.valueOf(request.getId()));
        if(!item.isPresent() || item.get().isStatus() == false){
            throw new Exception("Data Not Found");
        }
        ItemModel itemModel = item.get();
        itemModel.setTimeUpdated(LocalDateTime.now());
        itemModel.setStatus(false);
        itemRepository.save(itemModel);
    }



}
