package org.example.producer.service;

import lombok.AllArgsConstructor;
import org.example.producer.configuration.ApiEndpointConfiguration;
import org.example.producer.model.request.PlaceOrderRequest;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderService {

    private final KafkaTemplate<String, PlaceOrderRequest> kafkaTemplate;
    private final ApiEndpointConfiguration apiEndpointConfiguration;
    private final RestClient restClient;
    private static final String TOPIC = "order";


    public void placeOrderViaKafka(PlaceOrderRequest message) {
        UUID uuid = UUID.randomUUID();
        kafkaTemplate.send(TOPIC, uuid.toString(), message);
    }

    public void placeOrderViaApi(PlaceOrderRequest message) {
        restClient.post()
                .uri(URI.create(apiEndpointConfiguration.getConsumerUrl() + "/order"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(message)
                .retrieve()
                .toBodilessEntity();
    }
}
