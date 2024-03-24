package org.example.consumer.controller;

import lombok.AllArgsConstructor;
import org.example.consumer.model.request.PlaceOrderRequest;
import org.example.consumer.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody @Validated PlaceOrderRequest request) {
        orderService.placeOrderApi(request);
        return ResponseEntity.ok("Place order success");
    }
}
