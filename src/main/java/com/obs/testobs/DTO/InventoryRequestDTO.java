package com.obs.testobs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDTO {

    private int id;
    private int itemId;
    private int qty;
    private String type;
    private int inventoryId;
}
