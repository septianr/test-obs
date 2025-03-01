package com.obs.testobs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {

    private int id;
    private String name;
    private int price;
}
