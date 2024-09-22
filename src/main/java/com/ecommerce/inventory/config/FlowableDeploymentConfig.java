package com.ecommerce.inventory.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlowableDeploymentConfig {
    private final RepositoryService repositoryService;

    @PostConstruct
    public void deployProcesses() {
        repositoryService.createDeployment()
                .addClasspathResource("processes/product-management-process.bpmn20.xml")
                .addClasspathResource("processes/complete-tasks.bpmn20.xml")
                .deploy();
    }
}
