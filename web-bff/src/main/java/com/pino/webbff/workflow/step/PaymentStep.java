package com.pino.webbff.workflow.step;

import com.pino.openapi.payment.model.PaymentPayRequestDTO;
import com.pino.openapi.payment.model.PaymentRefundRequestDTO;
import com.pino.webbff.client.PaymentClient;

import java.util.concurrent.ConcurrentMap;

public class PaymentStep extends BaseStep {

    private final ConcurrentMap<String, Object> contexts;
    private final PaymentClient paymentClient;

    public PaymentStep(ConcurrentMap<String, Object> contexts, PaymentClient paymentClient) {
        super();
        this.contexts = contexts;
        this.paymentClient = paymentClient;
    }

    @Override
    protected void executeSafe() {
        var amount = (Integer) contexts.get("amount");

        var paymentPayRequestDTO = new PaymentPayRequestDTO();
        paymentPayRequestDTO.setAmount(amount);

        paymentClient.pay(paymentPayRequestDTO);
    }

    @Override
    protected void revertSafe() {
        var amount = (Integer) contexts.get("amount");

        var paymentRefundRequestDTO = new PaymentRefundRequestDTO();
        paymentRefundRequestDTO.setAmount(amount);

        paymentClient.refund(paymentRefundRequestDTO);
    }
}
