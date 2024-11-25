package com.pino.webbff.workflow.step;

import com.pino.webbff.enums.WorkflowStepStatus;

public interface WorkflowStep {
    WorkflowStepStatus getStatus();

    Boolean execute();

    Boolean revert();
}
