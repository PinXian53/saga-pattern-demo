package com.pino.inventoryapi.handler;

import com.pino.inventoryapi.exception.BadRequestException;
import com.pino.openapi.inventory.model.CommonResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CommonResponseDTO<Void>> handleBadRequestException(HttpServletRequest req, BadRequestException e) {
        var commonResponseDTO = new CommonResponseDTO<Void>();
        commonResponseDTO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponseDTO);
    }
}
