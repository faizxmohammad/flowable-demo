package com.ecommerce.inventory.controller;


import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private static final String COMPLETE_PROCESS = "complete-process";

    private final RuntimeService runtimeService;


    @PutMapping("/complete-task/{providerId}")
    public String completeTask(@PathVariable String providerId){
        Map<String, Object> variables = new HashMap<>();
        variables.put("providerId", providerId);
        runtimeService.startProcessInstanceByKey(COMPLETE_PROCESS, variables);
        return "task has been completed";

    }

}
