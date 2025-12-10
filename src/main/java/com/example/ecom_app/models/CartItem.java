package com.example.ecom_app.models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CartItem {
    @Id
    private String itemId; // unique generated id while adding item to cart
    private String inventoryItemId; // points to the actual item in inventory
    private Integer qty;
}
