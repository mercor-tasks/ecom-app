package com.example.ecom_app.models;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private List<String> orderItemIds;
    private Date createTs;
}
