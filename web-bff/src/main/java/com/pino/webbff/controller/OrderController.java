package com.pino.webbff.controller;

import com.pino.webbff.dto.OrderAddRequestDTO;
import com.pino.webbff.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("add")
    public String addOrder(@RequestBody OrderAddRequestDTO requestDTO) {
        return orderService.orderProduct(requestDTO);
    }
}
