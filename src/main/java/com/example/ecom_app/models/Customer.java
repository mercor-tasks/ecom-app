package com.example.ecom_app.models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Customer {
    @Id
    private String customerId;
    private String name;
    private String cartId;
}
