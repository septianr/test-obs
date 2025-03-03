package com.obs.testobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel extends AuditTrailModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String orderNo;

    @ManyToOne
    @JoinColumn(name = "itemId" ,nullable = false)
    private ItemModel itemId;

    @Column(nullable = false)
    private int qty;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean status;

}
