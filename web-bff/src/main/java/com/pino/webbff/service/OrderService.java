package com.pino.webbff.service;

import com.pino.webbff.client.InventoryClient;
import com.pino.webbff.client.PaymentClient;
import com.pino.webbff.dto.OrderAddRequestDTO;
import com.pino.webbff.workflow.flow.AddOrderWorkflow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    public String orderProduct(OrderAddRequestDTO requestDTO) {
        var workflow = new AddOrderWorkflow(requestDTO, inventoryClient, paymentClient);
        return workflow.executeSteps() ? "success" : "failure";
    }

}
