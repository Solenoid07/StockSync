package com.stocksync.backend.controller;

import com.stocksync.backend.model.Order;
import com.stocksync.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <--- Imported List for the history

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // POST request to place an order
    // URL: http://localhost:9090/api/orders?userId=1&productId=1&quantity=1
    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(orderService.placeOrder(userId, productId, quantity));
    }

    // NEW ENDPOINT: Get History
    // URL: http://localhost:9090/api/orders?userId=1
    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(@RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}