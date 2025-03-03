package com.obs.testobs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private int orderId;
    private int qty;
    private int price;
    private int itemId;
    private int inventoryId;
}
