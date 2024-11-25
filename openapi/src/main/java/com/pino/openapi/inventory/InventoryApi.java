package com.pino.openapi.inventory;

import com.pino.openapi.inventory.model.AmountDTO;
import com.pino.openapi.inventory.model.CommonResponseDTO;
import com.pino.openapi.inventory.model.InventoryAddRequestDTO;
import com.pino.openapi.inventory.model.InventoryDeductRequestDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface InventoryApi {
    @PostMapping("api/inventory/deduct")
    ResponseEntity<CommonResponseDTO<AmountDTO>> deduct(@RequestBody InventoryDeductRequestDTO requestDTO);

    @PostMapping("api/inventory/add")
    ResponseEntity<CommonResponseDTO<AmountDTO>> add(@RequestBody InventoryAddRequestDTO requestDTO);
}
