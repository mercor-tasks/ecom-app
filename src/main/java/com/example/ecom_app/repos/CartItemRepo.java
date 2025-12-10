package com.example.ecom_app.repos;

import com.example.ecom_app.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, String> {
}
