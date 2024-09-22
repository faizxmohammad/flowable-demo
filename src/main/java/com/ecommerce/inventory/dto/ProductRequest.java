package com.ecommerce.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {

    @JsonProperty("provider_id")
    @NotNull(message = "provider id is mandatory")
    private Long providerId;

    @JsonProperty("products")
    private List<ProductDto> products;

}
