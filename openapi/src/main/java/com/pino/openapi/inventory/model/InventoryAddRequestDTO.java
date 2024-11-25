package com.pino.openapi.inventory.model;

import lombok.Data;

@Data
public class InventoryAddRequestDTO {
    private String productId;
    private Integer count;
}
