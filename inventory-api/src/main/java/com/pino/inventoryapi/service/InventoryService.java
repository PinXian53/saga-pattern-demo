package com.pino.inventoryapi.service;

import com.pino.inventoryapi.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InventoryService {

    // 預設 10 筆庫存
    private final AtomicInteger inventoryCount = new AtomicInteger(10);

    public void incrementInventoryCount(int count) {
        inventoryCount.updateAndGet(value -> value + count);
    }

    public void decrementInventoryCount(int count) {
        inventoryCount.updateAndGet(value -> {
            if (count > value) {
                throw new BadRequestException("Insufficient inventory");
            }
            return value - count;
        });
    }
}
