package com.ecommerce.inventory.delegators;

import lombok.RequiredArgsConstructor;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CompleteTaskDelegator implements JavaDelegate {

    private final TaskService taskService;
    @Override
    public void execute(DelegateExecution execution) {
        String providerId = (String) execution.getVariable("providerId");
        Task task = taskService.createTaskQuery().taskAssignee(providerId).singleResult();
        if(task != null) {
            taskService.complete(task.getId());
        }

    }
}
