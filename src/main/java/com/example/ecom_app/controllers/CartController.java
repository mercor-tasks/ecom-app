package com.example.ecom_app.controllers;

import com.example.ecom_app.models.CartItem;
import com.example.ecom_app.models.dtos.AddItemToCartRequest;
import com.example.ecom_app.services.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Should validate if customerId is not null/empty (referenced from readme) [else return 400]
     * Should validate if customer is present in the database [else return 400]
     * Should validate if cartId is not null for the customer [else return 404]
     * Should validate if cart is present for the cartId [else return 404]
     * @param customerId
     * @return
     */
    @GetMapping("/get-cart-items")
    public List<CartItem> getCartItems(@RequestParam String customerId) {
        return cartService.getCartItems(customerId);
    }

    /**
     * Should validate if inventoryItemId is not null/empty (referenced from readme) [else return 400]
     * Should validate if qty is not null/empty and valid integer (referenced from readme) [else return 400]
     * Should validate if customer is present in the database [else return 400]
     * Should validate if cartId is not null for the customer [else return 500, as this is a server error that happened earlier during customer account creation, needs manual intervention]
     * Should validate if cart is present for the cartId [else return 500, as this is a server error that happened earlier during customer account creation, needs manual intervention]
     * Validate if inventoryItem is present for inventoryItemId [else return 400]
     * Validate if inventoryItem has enough qty available [else return 400]
     * If cart already has the same item, it should add to the qty
     */
    @PostMapping("/add-item-to-cart")
    public CartItem addItemToCart(@RequestBody AddItemToCartRequest addItemToCartRequest) {
        return cartService.addItemToCart(addItemToCartRequest);
    }
}
