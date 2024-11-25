package com.pino.inventoryapi.controller;


import com.pino.inventoryapi.service.InventoryService;
import com.pino.openapi.inventory.InventoryApi;
import com.pino.openapi.inventory.model.AmountDTO;
import com.pino.openapi.inventory.model.CommonResponseDTO;
import com.pino.openapi.inventory.model.InventoryAddRequestDTO;
import com.pino.openapi.inventory.model.InventoryDeductRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class InventoryController implements InventoryApi {

    // 假設每樣商品 100 元
    private static final int PRODUCT_AMOUNT = 100;

    private final InventoryService inventoryService;

    @Override
    public ResponseEntity<CommonResponseDTO<AmountDTO>> deduct(@RequestBody InventoryDeductRequestDTO requestDTO) {
        inventoryService.decrementInventoryCount(requestDTO.getCount());

        int totalAmount = PRODUCT_AMOUNT * requestDTO.getCount();

        var result = new CommonResponseDTO<AmountDTO>();
        result.setMessage("Success");
        result.setContent(new AmountDTO(totalAmount));
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<CommonResponseDTO<AmountDTO>> add(@RequestBody InventoryAddRequestDTO requestDTO) {
        inventoryService.incrementInventoryCount(requestDTO.getCount());

        int totalAmount = PRODUCT_AMOUNT * requestDTO.getCount();

        var result = new CommonResponseDTO<AmountDTO>();
        result.setMessage("Success");
        result.setContent(new AmountDTO(totalAmount));
        return ResponseEntity.ok().body(result);
    }

}
