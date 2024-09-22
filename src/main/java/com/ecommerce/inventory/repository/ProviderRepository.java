package com.ecommerce.inventory.repository;

import com.ecommerce.inventory.dto.ProviderRequest;
import com.ecommerce.inventory.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query(value = "Select * from provider where provider_name = ?1", nativeQuery = true)
    Optional<Provider> existsByName(String name);
}
