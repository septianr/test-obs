package com.obs.testobs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryModel extends AuditTrailModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemModel itemId;

    @Column(nullable = false)
    private int qty;

    @Column(length = 1, nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean status;
}
