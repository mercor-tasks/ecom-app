package com.example.ecom_app.repos;

import com.example.ecom_app.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, String> {
}
