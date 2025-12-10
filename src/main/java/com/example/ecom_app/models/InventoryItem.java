package com.example.ecom_app.models;

import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryItem {
    @Id
    private String itemId;
    private String name;
    private Integer availableQty;
    private BigDecimal unitPrice;
}
