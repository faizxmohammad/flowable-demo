package com.ecommerce.inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("name")
    @NotBlank(message = "product name is mandatory")
    @NotNull(message = "product name is mandatory")
    private String name;

    @JsonProperty("description")
    @NotBlank(message = "product description is mandatory")
    @NotNull(message = "product description is mandatory")
    private String description;

    @JsonProperty("price")
    @NotNull(message = "product price is mandatory")
    private BigDecimal price;

    @JsonProperty("stock_quantity")
    @NotNull(message = "product stockQuantity is mandatory")
    private Integer stockQuantity;

    @JsonProperty("barcode")
    @NotBlank(message = "product barcode is mandatory")
    @NotNull(message = "product barcode is mandatory")
    private String barcode;

}
