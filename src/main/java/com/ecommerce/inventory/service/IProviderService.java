package com.ecommerce.inventory.service;

import com.ecommerce.inventory.dto.ProviderRequest;
import com.ecommerce.inventory.models.Provider;

public interface IProviderService {
    Long addProvider(ProviderRequest providerRequest);
}
