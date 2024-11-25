package com.pino.webbff.dto;

import lombok.Data;

@Data
public class OrderAddRequestDTO {
    private String productId;
    private Integer count;
}
