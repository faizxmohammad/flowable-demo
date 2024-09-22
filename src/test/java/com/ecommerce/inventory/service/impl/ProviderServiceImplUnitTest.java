package com.ecommerce.inventory.service.impl;

import com.ecommerce.inventory.dto.ProviderRequest;
import com.ecommerce.inventory.models.Provider;
import com.ecommerce.inventory.repository.ProviderRepository;
import com.ecommerce.inventory.service.impl.factory.ProductFactory;
import com.ecommerce.inventory.service.impl.factory.ProviderFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProviderServiceImplUnitTest {

    @InjectMocks
    private ProviderServiceImpl providerService;

    @Mock
    private ProviderRepository providerRepository;


    @Test
    void addProviderTest() {
        ProviderRequest providerRequest = ProviderFactory.buildProviderRequest();
        when(providerRepository.existsByName(anyString())).thenReturn(Optional.empty());
        when(providerRepository.save(any())).thenReturn(ProviderFactory.buildProvider());

        Long providerId = providerService.addProvider(providerRequest);
        assertEquals(121L, providerId);
    }
}
