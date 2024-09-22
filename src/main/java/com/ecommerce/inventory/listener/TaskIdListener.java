package com.ecommerce.inventory.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;


@Component
public class TaskIdListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setVariable("currentTaskId", delegateTask.getId());
    }
}
