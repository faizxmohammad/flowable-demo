package com.ecommerce.inventory.validations.annotations;

import com.ecommerce.inventory.dto.ProductDto;
import com.ecommerce.inventory.dto.ProductRequest;
import com.ecommerce.inventory.utils.CommonUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;


@Component
public class ProductRequestValidator implements ConstraintValidator<ValidProductRequest, ProductRequest> {
    @Override
    public void initialize(ValidProductRequest constraintAnnotation) {
        // Nothing to initialize
    }

    @Override
    public boolean isValid(ProductRequest productRequest, ConstraintValidatorContext constraintValidatorContext) {
        return isValidRequest(productRequest);
    }

    private boolean isValidRequest(ProductRequest productRequest) {
        var providerId = productRequest.getProviderId();
        if(CommonUtils.isNullOrEmpty(providerId) || CommonUtils.isNullOrEmpty(productRequest.getProducts())) {
            return false;
        }
        return validateProductRequest(productRequest);

    }

    private boolean validateProductRequest(ProductRequest productRequest) {
       for(ProductDto productDto : productRequest.getProducts()){
           if(CommonUtils.isNullOrEmpty(productDto.getBarcode())){
               return false;
           }

           if(CommonUtils.isNullOrEmpty(productDto.getDescription())){
               return false;
           }

           if(CommonUtils.isNullOrEmpty(productDto.getPrice())){
               return false;
           }

           if(CommonUtils.isNullOrEmpty(productDto.getStockQuantity())){
               return false;
           }

           if(CommonUtils.isNullOrEmpty(productDto.getName())){
               return false;
           }
       }
        return true;
    }

}
