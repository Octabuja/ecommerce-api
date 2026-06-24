package com.octavio.ecommerce_api.controller;


import com.octavio.ecommerce_api.dto.OrderResponseDTO;
import com.octavio.ecommerce_api.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping("/cart/{cartId}")
    public OrderResponseDTO createOrder(
            @PathVariable Long cartId) {

        return orderService.createOrder(
                cartId);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(
            @PathVariable Long orderId) {

        return orderService.getOrderById(
                orderId);
    }

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {

        return orderService.getAllOrders();
    }
}
