package com.ecommerce.inventory.controller;


import com.ecommerce.inventory.dto.ProviderRequest;
import com.ecommerce.inventory.service.IProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/provider")
public class ProviderController {

    private final IProviderService providerService;

    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> addProvider(@RequestBody ProviderRequest providerRequest){
        log.info("Request Received: ADD [PROVIDER]");
        Long providerId = providerService.addProvider(providerRequest);
        Map<String,Object> response = new HashMap<>();
        response.put("message","Provider created successfully");
        response.put("id",providerId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
