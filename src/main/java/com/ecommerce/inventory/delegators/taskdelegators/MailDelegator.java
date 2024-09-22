package com.ecommerce.inventory.delegators.taskdelegators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MailDelegator implements JavaDelegate {
    private final TaskService taskService;

    @Override
    public void execute(DelegateExecution execution) {
        // send email.
        log.info("sending email");
        log.info("email sent");

        String taskId = execution.getCurrentActivityId();

        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();

        if(task != null){
            taskService.complete(taskId);
        }
    }
}
