//package com.ecommerce.inventory.service.impl;
//
//
//import com.ecommerce.inventory.dto.ProductRequest;
//import com.ecommerce.inventory.dto.ProductResponse;
//import com.ecommerce.inventory.models.Product;
//import com.ecommerce.inventory.models.StockQuantity;
//import com.ecommerce.inventory.repository.ProductRepository;
//import com.ecommerce.inventory.repository.ProviderRepository;
//import com.ecommerce.inventory.repository.StockQuantityRepository;
//import com.ecommerce.inventory.service.impl.factory.ProductFactory;
//import com.ecommerce.inventory.service.impl.factory.StockFactory;
//import org.flowable.engine.RuntimeService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ProductServiceImplUnitTest {
//
//    @InjectMocks
//    private ProductServiceImpl productService;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private StockQuantityRepository stockQuantityRepository;
//
//    @Mock
//    private ProviderRepository providerRepository;
//
//    @Mock
//    private RuntimeService runtimeService;
//
//
//    @Test
//    void testGetAllProducts(){
//        List<Product> products = ProductFactory.getProducts();
//        when(productRepository.findAll()).thenReturn(products);
//        ProductResponse response = productService.getAllProducts();
//        Assertions.assertFalse(response.getProducts().isEmpty());
//        Assertions.assertEquals(12L, response.getProducts().getFirst().getProductId());
//    }
//
//    @Test
//    void addProducts() throws IllegalAccessException {
//        ProductRequest productRequest = ProductFactory.buildProductRequest();
//        Optional<StockQuantity> stockQuantity = StockFactory.getStockQuantity();
//        StockQuantity quantity = stockQuantity.get();
//        when(productRepository.save(any())).thenReturn(new Product());
//        when(stockQuantityRepository.findByProductId(any())).thenReturn(stockQuantity);
//        when(stockQuantityRepository.save(any())).thenReturn(quantity);
//        productService.addProduct(productRequest.getProducts(), productRequest.getProviderId());
//        verify(productRepository,times(productRequest.getProducts().size())).save(any());
//        verify(stockQuantityRepository, times(productRequest.getProducts().size())).save(any());
//    }
//
//    @Test
//    void updateProduct() throws IllegalAccessException {
//        ProductRequest productRequest = ProductFactory.buildProductRequest();
//        Optional<StockQuantity> stockQuantity = StockFactory.getStockQuantity();
//        StockQuantity quantity = stockQuantity.get();
//        when(productRepository.existsById(any())).thenReturn(true);
//        when(stockQuantityRepository.findByProductId(any())).thenReturn(stockQuantity);
//        when(stockQuantityRepository.save(any())).thenReturn(quantity);
//
//        productService.updateProduct(productRequest.getProducts(), productRequest.getProviderId());
//        verify(productRepository,times(productRequest.getProducts().size())).save(any());
//        verify(stockQuantityRepository, times(productRequest.getProducts().size())).save(any());
//
//    }
//
//
//    @Test
//    void deleteProduct(){
//        ProductRequest productRequest = ProductFactory.buildProductRequest();
//        Optional<StockQuantity> stockQuantity = StockFactory.getStockQuantity();
//        StockQuantity quantity = stockQuantity.get();
//        when(productRepository.existsById(any())).thenReturn(true);
//        when(stockQuantityRepository.findByProductId(any())).thenReturn(stockQuantity);
//        when(stockQuantityRepository.save(any())).thenReturn(quantity);
//        productService.deleteProduct(productRequest.getProducts(), productRequest.getProviderId());
//        verify(stockQuantityRepository, times(productRequest.getProducts().size())).save(any());
//    }
//
//
//}
