package com.example.ecom_app.repos;

import com.example.ecom_app.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, String> {
}
