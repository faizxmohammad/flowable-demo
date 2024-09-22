package com.ecommerce.inventory.delegators.deprecated;

import com.ecommerce.inventory.dto.ProductRequest;
import com.ecommerce.inventory.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDeleteDelegate implements JavaDelegate {
    private final IProductService productService;

    @Override
    public void execute(DelegateExecution execution) {
        ProductRequest productRequest = (ProductRequest) execution.getVariable("productRequest");
        productService.deleteProduct(productRequest.getProducts(), productRequest.getProviderId(), execution.getId());
    }
}
