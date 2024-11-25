package com.pino.openapi.inventory.model;

import lombok.Data;

@Data
public class CommonResponseDTO<T> {
    String message;
    T content;
}
