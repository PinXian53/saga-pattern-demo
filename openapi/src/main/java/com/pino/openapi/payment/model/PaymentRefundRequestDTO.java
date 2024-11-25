package com.pino.openapi.payment.model;

import lombok.Data;

@Data
public class PaymentRefundRequestDTO {
    String userId;
    Integer amount;
}
