package com.ecommerce.inventory.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderRequest {

    @JsonProperty("provider_name")
    private String providerName;

    @JsonProperty("provider_address")
    private String providerAddress;
}
