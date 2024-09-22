package com.ecommerce.inventory.service.impl;

import com.ecommerce.inventory.dto.ProviderRequest;
import com.ecommerce.inventory.models.Provider;
import com.ecommerce.inventory.repository.ProviderRepository;
import com.ecommerce.inventory.service.IProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements IProviderService {

    private final ProviderRepository providerRepository;
    @Override
    public Long addProvider(ProviderRequest providerRequest) {
        if(!providerExistsByName(providerRequest.getProviderName())){
            Provider provider = buildProvider(providerRequest);
            Provider result = this.providerRepository.save(provider);
            return result.getProviderId();
        }

        throw new DuplicateKeyException("Cannot process your request, Provider already exists with this name");
    }

    private Provider buildProvider(ProviderRequest providerRequest){
        return Provider.builder()
                .providerName(providerRequest.getProviderName())
                .providerAddress(providerRequest.getProviderAddress())
                .build();
    }

    private boolean providerExistsByName(String providerName){
        Optional<Provider> provider = this.providerRepository.existsByName(providerName);
        return provider.isPresent();
    }

}
