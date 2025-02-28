package com.obs.testobs.model;

import jakarta.persistence.*;

public class InventoryModel extends AuditTrailModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    private ItemModel itemId;

    private int qty;

    @Column(length = 1)
    private String type;
}
