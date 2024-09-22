package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.dto.ProductRequest;
import com.ecommerce.inventory.dto.Response;
import com.ecommerce.inventory.service.IProductService;
import com.ecommerce.inventory.validations.annotations.ValidProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private static final String MESSAGE = "message";
    private static final String PROVIDER_ID = "providerId";
    private static final String PRODUCT_REQUEST = "products";
    private static final String HTTP_METHOD = "httpMethod";

    private static final String PRODUCT_PROCESS = "product-management-process";

    private final IProductService productService;
    private final RuntimeService runtimeService;

    private final TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<Response> getAllProducts(){
        log.info("Request Received: GET [PRODUCTS]");
        var allProducts = productService.getAllProducts();
        Response response = Response.builder()
                .success(true)
                .response(allProducts)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Map<String,String>> addProduct(@ValidProductRequest @RequestBody ProductRequest productRequest) {
        log.info("Request Received: ADD [PRODUCTS]");

        // Start Flowable process instance
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROVIDER_ID, productRequest.getProviderId());
        variables.put(PRODUCT_REQUEST, productRequest.getProducts());
        variables.put(HTTP_METHOD, "ADD");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PRODUCT_PROCESS, variables);
        Map<String, String> response = new HashMap<>();
        boolean isNotValidProvider = (boolean) processInstance.getProcessVariables().get("isValidProvider");
        boolean isOperationSuccess = (boolean) processInstance.getProcessVariables().get("operationSuccess");
        if(!isNotValidProvider || !isOperationSuccess){
            response.put(MESSAGE,"Error adding Product, please try again.");
        }else{
            response.put(MESSAGE, "Product Added to inventory.");
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Map<String,String>> updateProduct(@RequestBody ProductRequest productRequest) throws IllegalAccessException {
        log.info("Request Received: UPDATE [PRODUCTS]");

        // Populate Flowable process instance variables
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROVIDER_ID, productRequest.getProviderId());
        variables.put(PRODUCT_REQUEST, productRequest.getProducts());
        variables.put(HTTP_METHOD, "PUT");

        runtimeService.startProcessInstanceByKey(PRODUCT_PROCESS, variables);

        Map<String, String> response = new HashMap<>();
        response.put(MESSAGE, "Product/Products Updated Successfully");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Map<String,String>> deleteProduct(@RequestBody ProductRequest productRequest){
        log.info("Request Received: DELETE [PRODUCTS]");

        // Start Flowable process instance
        Map<String, Object> variables = new HashMap<>();
        variables.put(PROVIDER_ID, productRequest.getProviderId());
        variables.put(PRODUCT_REQUEST, productRequest.getProducts());
        variables.put(HTTP_METHOD, "DELETE");

        runtimeService.startProcessInstanceByKey(PRODUCT_PROCESS, variables);

        Map<String, String> response = new HashMap<>();
        response.put(MESSAGE, "Product/Products Updated Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
