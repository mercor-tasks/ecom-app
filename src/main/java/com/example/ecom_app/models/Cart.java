package com.example.ecom_app.models;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class Cart {
    @Id
    private String cartId;
    private List<String> cartItemIds;
}
