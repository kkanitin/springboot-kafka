package org.example.producer.controller;

import lombok.AllArgsConstructor;
import org.example.producer.model.request.PlaceOrderRequest;
import org.example.producer.service.OrderService;
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

    @PostMapping("/kafka")
    public String kafka(@RequestBody @Validated PlaceOrderRequest message) {
        orderService.placeOrderViaKafka(message);
        return "send";
    }

    @PostMapping("/api")
    public String noKafka(@RequestBody @Validated PlaceOrderRequest message) {
        orderService.placeOrderViaApi(message);
        return "send";
    }
}
