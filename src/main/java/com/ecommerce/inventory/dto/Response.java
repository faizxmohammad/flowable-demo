package com.ecommerce.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import org.checkerframework.checker.index.qual.SearchIndexBottom;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    @JsonProperty("error_details")
    private Error error;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("product_response")
    private ProductResponse response;

}





