package com.ecommerce.inventory.repository;

import com.ecommerce.inventory.models.StockQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockQuantityRepository extends JpaRepository<StockQuantity,Long> {


    @Query(value = "SELECT * from stock_quantity s where product_id = ?1",nativeQuery = true)
    Optional<StockQuantity> findByProductId(Long productId);
}
