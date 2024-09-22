package com.ecommerce.inventory.service.impl.factory;

import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.dto.ProductRequest;
import com.ecommerce.inventory.models.Product;
import com.ecommerce.inventory.models.Provider;
import com.ecommerce.inventory.models.StockQuantity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

    public static List<Product> getProducts(){
        Product product1 = Product.builder()
                .productId(12L)
                .provider(Provider.builder()
                        .providerId(121L)
                        .build())
                .price(BigDecimal.valueOf(1200.00))
                .name("product1")
                .barcode("123BCDF8")
                .stockQuantity(StockQuantity.builder()
                        .quantity(12)
                        .build())
                .build();

        Product product2 = Product.builder()
                .productId(12L)
                .provider(Provider.builder()
                        .providerId(121L)
                        .build())
                .price(BigDecimal.valueOf(1200.00))
                .name("product1")
                .barcode("123BCDF8")
                .stockQuantity(StockQuantity.builder()
                        .quantity(12)
                        .build())
                .build();

        Product product3 = Product.builder()
                .productId(12L)
                .provider(Provider.builder()
                        .providerId(121L)
                        .build())
                .price(BigDecimal.valueOf(1200.00))
                .name("product1")
                .barcode("123BCDF8")
                .stockQuantity(StockQuantity.builder()
                        .quantity(12)
                        .build())
                .build();

        List<Product> products = new ArrayList<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }


    public static ProductRequest buildProductRequest(){
        Long providerId = 121L;
        return ProductRequest.builder()
                .providerId(providerId)
                .products(buildProductDto())
                .build();

    }

    private static List<ProductDto> buildProductDto() {

        List<ProductDto> products = new ArrayList<>();
        ProductDto product1 = ProductDto.builder()
                .productId(1L)
                .stockQuantity(30)
                .description("description")
                .price(BigDecimal.valueOf(1200))
                .name("product1")
                .build();

        ProductDto product2 = ProductDto.builder()
                .productId(2L)
                .stockQuantity(30)
                .description("description")
                .price(BigDecimal.valueOf(200))
                .name("product2")
                .build();


        ProductDto product3 = ProductDto.builder()
                .productId(3L)
                .stockQuantity(30)
                .description("description")
                .price(BigDecimal.valueOf(100))
                .name("product3")
                .build();

        products.add(product1);
        products.add(product2);
        products.add(product3);
        return products;
    }
}
