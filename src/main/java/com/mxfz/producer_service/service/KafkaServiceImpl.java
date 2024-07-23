package com.mxfz.producer_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Mono<Void> sendKafkaMessage(String payload, String topic) {
        Message<String> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        return sendMessageAsynchronously(message);
    }

    public Mono<Void> sendKafkaMessageWithHeaders(String payload, String topic,
                                                  Map<String, String> headerMap) {
        var messageBuilder = MessageBuilder.withPayload(payload);
        messageBuilder.setHeader(KafkaHeaders.TOPIC, topic);
        if (!Objects.isNull(headerMap) && !headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                messageBuilder.setHeader(entry.getKey(), entry.getValue());
            }
        }
        Message<String> message = messageBuilder.build();
        return sendMessageAsynchronously(message);
    }

    private Mono<Void> sendMessageAsynchronously(Message<String> message) {
        return Mono.create(monoSink -> kafkaTemplate.send(message)
                .whenComplete((result, error) -> {
                    if (Objects.nonNull(error)) {
                        monoSink.error(error);
                    } else {
                        log.info("Published Message to Topic: {}", message.getHeaders().get(KafkaHeaders.TOPIC));
                        log.info("Message: {} \n Offset: {}", message.getPayload(), result.getRecordMetadata());
                        monoSink.success();
                    }
                }));
    }
}