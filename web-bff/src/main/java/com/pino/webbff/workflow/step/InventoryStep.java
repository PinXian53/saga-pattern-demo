package com.pino.webbff.workflow.step;

import com.pino.openapi.inventory.model.InventoryAddRequestDTO;
import com.pino.openapi.inventory.model.InventoryDeductRequestDTO;
import com.pino.webbff.client.InventoryClient;
import com.pino.webbff.dto.OrderAddRequestDTO;

import java.util.concurrent.ConcurrentMap;

public class InventoryStep extends BaseStep {

    private final ConcurrentMap<String, Object> contexts;
    private final InventoryClient inventoryClient;
    private final OrderAddRequestDTO requestDTO;

    public InventoryStep(ConcurrentMap<String, Object> contexts, InventoryClient inventoryClient, OrderAddRequestDTO requestDTO) {
        super();
        this.contexts = contexts;
        this.inventoryClient = inventoryClient;
        this.requestDTO = requestDTO;
    }


    @Override
    protected void executeSafe() {
        var inventoryDeductRequestDTO = new InventoryDeductRequestDTO();
        inventoryDeductRequestDTO.setProductId(requestDTO.getProductId());
        inventoryDeductRequestDTO.setCount(requestDTO.getCount());

        int amount = inventoryClient.deduct(inventoryDeductRequestDTO).getBody().getContent().getAmount();
        contexts.put("amount", amount);
    }

    @Override
    protected void revertSafe() {
        var inventoryAddRequestDTO = new InventoryAddRequestDTO();
        inventoryAddRequestDTO.setProductId(requestDTO.getProductId());
        inventoryAddRequestDTO.setCount(requestDTO.getCount());

        inventoryClient.add(inventoryAddRequestDTO);
    }
}
