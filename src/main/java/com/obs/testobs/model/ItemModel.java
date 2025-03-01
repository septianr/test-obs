package com.obs.testobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel extends AuditTrailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int price;
    private boolean status;
}
