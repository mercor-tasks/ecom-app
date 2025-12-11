package com.example.ecom_app.controllers;

import com.example.ecom_app.models.Order;
import com.example.ecom_app.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Should validate if customerId is not null/empty (referenced from readme) [else return 400]
     * Should validate if customer is present in the database [else return 400]
     * Should validate if cartId is not null for the customer [else return 500, as this is a server error that happened earlier during customer account creation, needs manual intervention]
     * Should validate if cart is present for the cartId [else return 500, as this is a server error that happened earlier during customer account creation, needs manual intervention]
     * Validate if inventoryItem is present for inventoryItemId [else return 400]
     * Validate if inventoryItem has enough qty available before placing the order [else return 400]
     */
    @PostMapping("/place-order")
    public Order placeOrder(@RequestParam String customerId) {
        return orderService.placeOrderFromCustomerCart(customerId);
    }
}
