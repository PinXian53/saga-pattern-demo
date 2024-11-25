package com.pino.webbff.workflow.flow;

import com.pino.webbff.enums.WorkflowStepStatus;
import com.pino.webbff.workflow.step.WorkflowStep;

import java.util.List;

public interface Workflow {

    List<WorkflowStep> getSteps();

    default boolean executeSteps() {
        for (var step : getSteps()) {
            if (!step.execute()) {
                break;
            }
        }
        if (isCompleted()) {
            return true;
        }
        for (var step : getCompleteSteps()) {
            if (!step.revert()) {
                break;
            }
        }
        return false;
    }


    default List<WorkflowStep> getCompleteSteps() {
        return getSteps().stream()
                .filter(step -> WorkflowStepStatus.COMPLETE == step.getStatus())
                .toList();
    }

    default boolean isCompleted() {
        return getSteps().size() == getCompleteSteps().size();
    }

}
