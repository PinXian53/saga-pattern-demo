package com.pino.webbff.workflow.flow;

import com.pino.webbff.client.InventoryClient;
import com.pino.webbff.client.PaymentClient;
import com.pino.webbff.dto.OrderAddRequestDTO;
import com.pino.webbff.workflow.step.InventoryStep;
import com.pino.webbff.workflow.step.PaymentStep;
import com.pino.webbff.workflow.step.WorkflowStep;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
public class AddOrderWorkflow implements Workflow {

    private final List<WorkflowStep> steps;
    private final ConcurrentMap<String, Object> contexts = new ConcurrentHashMap<>();

    public AddOrderWorkflow(OrderAddRequestDTO requestDTO, InventoryClient inventoryClient, PaymentClient paymentClient) {
        var inventoryStep = new InventoryStep(contexts, inventoryClient, requestDTO);
        var paymentStep = new PaymentStep(contexts, paymentClient);

        this.steps = List.of(inventoryStep, paymentStep);
    }

}
