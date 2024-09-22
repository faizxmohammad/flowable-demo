package com.ecommerce.inventory.delegators;

import com.ecommerce.inventory.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProviderDelegator implements JavaDelegate {

    private final ProviderRepository providerRepository;

    @Override
    public void execute(DelegateExecution execution) {
        Long providerId = (Long) execution.getVariable("providerId");
        if (!providerRepository.existsById(providerId)) {
            log.error("Invalid Request [ADD]: Provider does not exits with Id: {}", providerId);
            execution.setVariable("isValidProvider", false);
            execution.setVariable("executionId", execution.getId());
        }else{
            execution.setVariable("isValidProvider", true);
        }
    }
}
