package com.ecommerce.inventory.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class FlowableDeploymentChecker {

    private final RepositoryService repositoryService;

    @PostConstruct
    public void checkDeployments() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        processDefinitions.forEach(pd -> {
           log.info("Deployed process: {} Version: {} ", pd.getKey()  , pd.getVersion());
        });
    }
}
