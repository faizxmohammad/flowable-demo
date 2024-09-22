package com.ecommerce.inventory.validations.annotations;

import com.ecommerce.inventory.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class ProductValidator implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        String productRequest = (String) execution.getVariable("products");
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.readValue(productRequest, new TypeReference<List<ProductDto>>() {});
        } catch (JsonProcessingException e) {
            log.error("Error [{}]", e.getMessage(), e);
        }
    }
}
