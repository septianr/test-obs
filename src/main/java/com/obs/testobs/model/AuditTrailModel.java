package com.obs.testobs.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AuditTrailModel {

    @Column(name = "time_created", updatable = false)
    private LocalDateTime TimeCreated;

    @Column(name = "time_updated", updatable = false)
    private LocalDateTime TimeUpdated;
}
