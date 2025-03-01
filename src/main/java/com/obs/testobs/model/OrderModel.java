package com.obs.testobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel extends AuditTrailModel{

    @Id
    private String orderNo;

    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private ItemModel itemId;

    private int qty;

    private int price;
    private boolean status;

}
