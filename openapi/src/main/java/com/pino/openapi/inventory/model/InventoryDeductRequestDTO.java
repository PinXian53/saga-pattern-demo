package com.pino.openapi.inventory.model;

import lombok.Data;

@Data
public class InventoryDeductRequestDTO {
    private String productId;
    private Integer count;
}
