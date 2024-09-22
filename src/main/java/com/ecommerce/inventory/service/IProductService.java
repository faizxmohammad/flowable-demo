package com.ecommerce.inventory.service;

import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.dto.ProductRequest;
import com.ecommerce.inventory.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    void addProduct(List<ProductDto> products, Long providerId, String executionId);

    void updateProduct(List<ProductDto> products, Long providerId, String executionId);

    void deleteProduct(List<ProductDto> products, Long providerId, String executionId);

    ProductResponse getAllProducts();
}
