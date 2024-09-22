package com.ecommerce.inventory.service.impl;

import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.dto.ProductResponse;
import com.ecommerce.inventory.models.Product;
import com.ecommerce.inventory.models.Provider;
import com.ecommerce.inventory.models.StockQuantity;
import com.ecommerce.inventory.repository.ProductRepository;
import com.ecommerce.inventory.repository.ProviderRepository;
import com.ecommerce.inventory.repository.StockQuantityRepository;
import com.ecommerce.inventory.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final StockQuantityRepository stockQuantityRepository;
    private final ProviderRepository providerRepository;

    private final RuntimeService runtimeService;

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addProduct(List<ProductDto> products, Long providerId, String executionId) {
        for (ProductDto productDto : products) {
            if (!productExistsByName(productDto.getName())) { // No product available
                Product product = buildProduct(productDto, providerId);
                this.productRepository.save(product);
                updateStockQuantity(product.getProductId(), productDto.getStockQuantity());
                log.info("New product added: [{}]", productDto.getName());
                buildOperationResponseValueForProcess(executionId);
            } else {
                log.error("Product: [{}] already exists", productDto.getName());
                throw new DuplicateKeyException("Unable to Process the request, Product Already exists.");
            }
        }
    }

    private void buildOperationResponseValueForProcess(String executionId){
        runtimeService.setVariable(executionId, "operationSuccess", true);
        runtimeService.setVariable(executionId, "providerEmail", "gg@gg.com");
    }


    private boolean productExistsByName(String name) {
        Long productExists =  this.productRepository.findByName(name);
        return productExists != 0;
    }


    @Override
    public void updateProduct(List<ProductDto> products, Long providerId, String executionId) {
            for (ProductDto productDto : products) {
                if (productExistsById(productDto.getProductId())) {
                    Product product = buildProduct(productDto, providerId);
                    updateStockQuantity(productDto.getProductId(), productDto.getStockQuantity());
                    this.productRepository.save(product);
                    buildOperationResponseValueForProcess(executionId);
                    log.info("Product Updated with product id:{},{}", productDto.getProductId(), product);
                }
            }
        }

    @Override
    public void deleteProduct(List<ProductDto> products, Long providerId, String executionId) {
        for (ProductDto productDto : products) {
            var productId = productDto.getProductId();
            var stockQuantityProvided = productDto.getStockQuantity();
            if (productExistsById(productId)) {
                Optional<StockQuantity> stockQuantityOptional = getStockQuantity(productId);
                if (stockQuantityOptional.isPresent()) {
                    var currentStockQuantity = stockQuantityOptional.get().getQuantity();
                    if (currentStockQuantity < stockQuantityProvided) {
                        log.error("Invalid Request [DELETE] from Provider: {}, current stock quantity: {} < provided quantity: {}",
                                providerId, currentStockQuantity, stockQuantityProvided);
                        throw new IllegalArgumentException("Unable to process your request, Cannot Delete products.");
                    }
                    // Remove product from product table and stock Quantity table
                    if (currentStockQuantity.equals(stockQuantityProvided)) {
                        this.productRepository.deleteById(productId);
                        log.info("Product deleted with product id: {}", productId);
                        buildOperationResponseValueForProcess(executionId);
                    } else { // reduce the stock quantity size
                        StockQuantity stockQuantity = StockQuantity.builder()
                                .quantity(currentStockQuantity - stockQuantityProvided)
                                .product(Product.builder()
                                        .productId(productId)
                                        .build())
                                .build();
                        this.stockQuantityRepository.save(stockQuantity);
                        buildOperationResponseValueForProcess(executionId);
                        log.info("Product {} stock quantity has been reduced to: {} ",productId, currentStockQuantity - stockQuantityProvided);
                    }

                }
            }
        }

    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductDto> response = new ArrayList<>();
        ModelMapper modelMapper = getModelMapper();

        for (Product product : products) {
            ProductDto productDto = ProductDto.builder().build();
            modelMapper.map(product, productDto);
            response.add(productDto);
        }
        return ProductResponse.builder()
                .products(response)
                .build();
    }

    private boolean providerExistsById(Long providerId) {
        return this.providerRepository.existsById(providerId);
    }


    private Product buildProduct(ProductDto productDto, Long providerId) {
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .barcode(productDto.getBarcode())
                .provider(Provider.builder()
                        .providerId(providerId)
                        .build())
                .build();
    }

    private void updateStockQuantity(Long productId, Integer newStockSize) {
        Optional<StockQuantity> stockQuantityOptional = getStockQuantity(productId);
        StockQuantity stockQuantity;
        if (stockQuantityOptional.isPresent()) { // if stock exists, update the stock quantity
            stockQuantity = stockQuantityOptional.get();
            Integer currentStockQuantity = stockQuantity.getQuantity();
            stockQuantity.setQuantity(currentStockQuantity + newStockSize);
        } else { // Add new stock into table
            stockQuantity = StockQuantity.builder()
                    .quantity(newStockSize)
                    .product(Product.builder()
                            .productId(productId)
                            .build())
                    .build();
        }
        this.stockQuantityRepository.save(stockQuantity);
    }

    private Optional<StockQuantity> getStockQuantity(Long productId) {
        return this.stockQuantityRepository.findByProductId(productId);
    }

    private boolean productExistsById(Long productId) {
        return this.productRepository.existsById(productId);
    }
}
