package org.example.consumer.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.consumer.model.request.PlaceOrderRequest;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.topic.group-id}")
@Log4j2
public class KafkaService {

    private final OrderService orderService;

    @KafkaHandler
    public void receiveMessage(@Payload PlaceOrderRequest data) {
        log.info("Received message: "+ data);
        orderService.placeOrderKafka(data);
    }
}
