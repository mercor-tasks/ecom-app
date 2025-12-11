package com.example.ecom_app.services;

import com.example.ecom_app.models.*;
import com.example.ecom_app.repos.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRepo orderRepo;
    private CartRepo cartRepo;
    private CustomerRepo customerRepo;
    private CartItemRepo cartItemRepo;
    private OrderItemRepo orderItemRepo;
    private CartService cartService;

    public OrderService(OrderRepo orderRepo,
                        CartRepo cartRepo,
                        CustomerRepo customerRepo,
                        CartItemRepo cartItemRepo,
                        OrderItemRepo orderItemRepo,
                        CartService cartService) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.customerRepo = customerRepo;
        this.cartItemRepo = cartItemRepo;
        this.orderItemRepo = orderItemRepo;
        this.cartService = cartService;
    }

    public Order placeOrderFromCustomerCart(String customerId) {
        Customer customer = customerRepo.getReferenceById(customerId);
        String cartId = customer.getCartId();
        Cart cart = cartRepo.getReferenceById(cartId);
        List<CartItem> cartItems = cartService.getCartItems(customerId);

        List<OrderItem> orderItems = cartItems
                .stream()
                .filter(Objects::nonNull)
                .map(OrderService::cartItemToOrderItem)
                .toList();
        Order order = createOrder(customerId, orderItems);
        orderRepo.save(order);

        clearCart(cart);
        orderItemRepo.saveAll(orderItems);

        return order;
    }

    private void clearCart(Cart cart) {
        cart.getCartItemIds().clear();
        cartRepo.save(cart);
        cartItemRepo.deleteAllById(cart.getCartItemIds());
    }

    private static OrderItem cartItemToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(cartItem.getItemId());
        orderItem.setInventoryItemId(cartItem.getInventoryItemId());
        orderItem.setQty(cartItem.getQty());
        return orderItem;
    }

    private static Order createOrder(String customerId, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomerId(customerId);
        order.setOrderItemIds(orderItems.stream().map(OrderItem::getItemId).toList());
        order.setCreateTs(new Date());
        return order;
    }
}
