package com.example.ecom_app.repos;

import com.example.ecom_app.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<InventoryItem, String> {
}
