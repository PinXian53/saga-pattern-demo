package com.pino.openapi.payment;

import com.pino.openapi.inventory.model.CommonResponseDTO;
import com.pino.openapi.payment.model.PaymentPayRequestDTO;
import com.pino.openapi.payment.model.PaymentRefundRequestDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface PaymentApi {

    @PostMapping("api/payment/pay")
    ResponseEntity<CommonResponseDTO<Void>> pay(@RequestBody PaymentPayRequestDTO requestDTO);

    @PostMapping("api/payment/refund")
    ResponseEntity<CommonResponseDTO<Void>> refund(@RequestBody PaymentRefundRequestDTO requestDTO);
}
