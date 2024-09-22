package com.ecommerce.inventory.service.impl.factory;

import com.ecommerce.inventory.dto.ProviderRequest;
import com.ecommerce.inventory.models.Provider;

public class ProviderFactory {


    public static ProviderRequest buildProviderRequest(){
        return ProviderRequest.builder()
                .providerName("provider")
                .providerAddress("Address")
                .build();
    }

    public static Provider buildProvider(){
        return Provider.builder()
                .providerId(121L)
                .providerName("provider")
                .providerAddress("Address")
                .build();
    }
}
