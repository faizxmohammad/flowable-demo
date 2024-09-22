package com.ecommerce.inventory.repository;

import com.ecommerce.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT count(name) FROM product where name = ?1",nativeQuery = true)
    Long findByName(String productName);
}
