package com.example.ecom_app.models;

import jakarta.persistence.Id;

import java.util.List;

public class Order {
    @Id
    private String orderId;
    private String customerId;
    private List<CartItem> items;
}
