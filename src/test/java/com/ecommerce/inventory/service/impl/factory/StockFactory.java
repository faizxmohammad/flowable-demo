package com.ecommerce.inventory.service.impl.factory;

import com.ecommerce.inventory.models.StockQuantity;

import java.util.Optional;

public class StockFactory {


    public static Optional<StockQuantity> getStockQuantity(){
        return Optional.of(StockQuantity.builder()
                .quantity(300)
                .id(1L)
                .build());
    }
}
