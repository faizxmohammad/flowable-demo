package com.ecommerce.inventory.delegators;

import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.dto.ProductRequest;
import com.ecommerce.inventory.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.runtime.Execution;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;



@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDelegator implements JavaDelegate {
    private final IProductService productService;

    @Override
    @SuppressWarnings("all")
    public void execute(DelegateExecution execution) {
        String httpMethod = (String) execution.getVariable("httpMethod");
        Long providerId = (Long) execution.getVariable("providerId");
        List<ProductDto> products = (List<ProductDto>) execution.getVariable("products");
        String executionId = execution.getId();

        switch (httpMethod){
            case "ADD":
                productService.addProduct(products, providerId, executionId);
                setOperationResult(execution);
                break;

            case "PUT":
                productService.updateProduct(products, providerId, executionId);
                setOperationResult(execution);
                break;

            case "DELETE":
                productService.deleteProduct(products, providerId, executionId);
                setOperationResult(execution);
                break;

            default: return;
        }

    }


    private void setOperationResult(DelegateExecution execution){
        execution.setVariable("operationSuccess", true);
        execution.setVariable("inventoryName","products");
        execution.setVariable("phoneNo","123456789");
        execution.setVariable("from","customer@flowable.com");
        execution.setVariable("text","text");
        execution.setVariable("html","<h1>Hello this is a sample message</h1>");
    }
}
