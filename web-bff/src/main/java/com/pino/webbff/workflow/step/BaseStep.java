package com.pino.webbff.workflow.step;

import com.pino.webbff.enums.WorkflowStepStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public abstract class BaseStep implements WorkflowStep {

    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    protected final String stepName;

    protected BaseStep() {
        var clazz = super.getClass();
        this.stepName = clazz.getSimpleName();
    }


    public WorkflowStepStatus getStatus() {
        return stepStatus;
    }

    @Override
    public Boolean execute() {
        try {
            log.info("%s execute start".formatted(stepName));
            this.executeSafe();
            this.stepStatus = WorkflowStepStatus.COMPLETE;
            return true;
        }catch (Exception e) {
            log.error("%s execute error: %s".formatted(stepName, e.getMessage()), e);
            this.stepStatus = WorkflowStepStatus.FAILED;
            return false;
        }
    }
    @Override
    public Boolean revert() {
        try {
            log.info("%s revert start".formatted(stepName));
            this.revertSafe();
            this.stepStatus = WorkflowStepStatus.COMPLETE;
            return true;
        }catch (Exception e) {
            log.error("%s revert error: %s".formatted(stepName, e.getMessage()), e);
            this.stepStatus = WorkflowStepStatus.FAILED;
            // TODO revert error 應該要有其他處置
            return false;
        }
    }

    protected abstract void executeSafe();

    protected abstract void revertSafe();


}
