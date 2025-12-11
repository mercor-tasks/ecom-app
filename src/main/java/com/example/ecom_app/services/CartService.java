package com.example.ecom_app.services;

import com.example.ecom_app.models.Cart;
import com.example.ecom_app.models.CartItem;
import com.example.ecom_app.models.Customer;
import com.example.ecom_app.models.InventoryItem;
import com.example.ecom_app.models.dtos.AddItemToCartRequest;
import com.example.ecom_app.repos.CartItemRepo;
import com.example.ecom_app.repos.CartRepo;
import com.example.ecom_app.repos.CustomerRepo;
import com.example.ecom_app.repos.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CartService {
    private CustomerRepo customerRepo;
    private CartRepo cartRepo;
    private CartItemRepo cartItemRepo;
    private InventoryRepo inventoryRepo;

    public CartService(CustomerRepo customerRepo,
                       CartRepo cartRepo,
                       CartItemRepo cartItemRepo,
                       InventoryRepo inventoryRepo) {
        this.customerRepo = customerRepo;
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.inventoryRepo = inventoryRepo;
    }

    public List<CartItem> getCartItems(String customerId) {
        Customer customer = customerRepo.getReferenceById(customerId);
        String cartId = customer.getCartId();
        Cart cart = cartRepo.getReferenceById(cartId);
        List<String> cartItemIds = cart.getCartItemIds();
        return cartItemIds
                .stream()
                .filter(Objects::nonNull)
                .map(cartItemId -> cartItemRepo.getReferenceById(cartItemId))
                .toList();
    }

    public CartItem addItemToCart(AddItemToCartRequest addItemToCartRequest) {
        String customerId = addItemToCartRequest.getCustomerId();
        String inventoryItemId = addItemToCartRequest.getInventoryItemId();
        Integer reqQty = addItemToCartRequest.getRequestedQty();

        Customer customer = customerRepo.getReferenceById(customerId);
        String cartId = customer.getCartId();
        Cart cart = cartRepo.getReferenceById(cartId);

        InventoryItem inventoryItem = inventoryRepo.getReferenceById(inventoryItemId);
        inventoryItem.setAvailableQty(inventoryItem.getAvailableQty() - reqQty);

        CartItem cartItem = createCartItem(inventoryItemId, reqQty);

        cart.getCartItemIds().add(cartItem.getItemId());

        cartItemRepo.save(cartItem);
        cartRepo.save(cart);

        return cartItem;
    }

    private static CartItem createCartItem(String inventoryItemId, Integer reqQty) {
        CartItem cartItem = new CartItem();
        cartItem.setItemId(UUID.randomUUID().toString());
        cartItem.setInventoryItemId(inventoryItemId);
        cartItem.setQty(reqQty);
        return cartItem;
    }
}
