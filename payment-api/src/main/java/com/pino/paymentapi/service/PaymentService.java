package com.pino.paymentapi.service;

import com.pino.paymentapi.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PaymentService {

    // 預設戶頭餘額 200
    private final AtomicInteger depositAmount = new AtomicInteger(200);

    public void incrementDepositAmount(int count) {
        depositAmount.updateAndGet(value -> value + count);
    }

    public void decrementDepositAmount(int count) {
        depositAmount.updateAndGet(value -> {
            if (count > value) {
                throw new BadRequestException("Insufficient balance");
            }
            return value - count;
        });
    }
}
