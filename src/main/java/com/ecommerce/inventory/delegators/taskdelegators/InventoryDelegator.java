package com.ecommerce.inventory.delegators.taskdelegators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;


@Slf4j
@Component("inventoryDelegator")
@RequiredArgsConstructor
public class InventoryDelegator implements JavaDelegate {
    private final TaskService taskService;

    @Override
    public void execute(DelegateExecution execution) {
        log.info("adding product to inventory");
        buildTask(execution);
    }

    private void buildTask(DelegateExecution execution){
       Task task = taskService.newTask();
       task.setAssignee(String.valueOf(execution.getVariable("providerId")));
       task.setName("inventory-task");
       taskService.saveTask(task);
       log.info("Inventory will update every day at 1:00 AM");

    }
}
