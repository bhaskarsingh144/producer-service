package com.mxfz.producer_service.service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface KafkaService {
    Mono<Void> sendKafkaMessage(String payload, String topic);

    Mono<Void> sendKafkaMessageWithHeaders(String payload, String topic, Map<String, String> headerMap);
}
