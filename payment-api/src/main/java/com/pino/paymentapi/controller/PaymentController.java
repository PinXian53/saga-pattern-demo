package com.pino.paymentapi.controller;

import com.pino.openapi.inventory.model.CommonResponseDTO;
import com.pino.openapi.payment.PaymentApi;
import com.pino.openapi.payment.model.PaymentPayRequestDTO;
import com.pino.openapi.payment.model.PaymentRefundRequestDTO;
import com.pino.paymentapi.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class PaymentController implements PaymentApi {

    private final PaymentService paymentService;

    @Override
    public ResponseEntity<CommonResponseDTO<Void>> pay(@RequestBody PaymentPayRequestDTO requestDTO) {
        paymentService.decrementDepositAmount(requestDTO.getAmount());

        var commonResponseDTO = new CommonResponseDTO<Void>();
        commonResponseDTO.setMessage("Success");
        return ResponseEntity.ok().body(commonResponseDTO);
    }

    @Override
    public ResponseEntity<CommonResponseDTO<Void>> refund(@RequestBody PaymentRefundRequestDTO requestDTO) {
        paymentService.incrementDepositAmount(requestDTO.getAmount());

        var commonResponseDTO = new CommonResponseDTO<Void>();
        commonResponseDTO.setMessage("Success");
        return ResponseEntity.ok().body(commonResponseDTO);
    }

}
