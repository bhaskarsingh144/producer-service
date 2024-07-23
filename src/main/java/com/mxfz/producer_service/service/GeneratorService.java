package com.mxfz.producer_service.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class GeneratorService {

    private final KafkaService kafkaService;

    public GeneratorService(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostConstruct
    public void generateToTopic() throws InterruptedException {
        while (true) {
            Thread.sleep(Duration.ofSeconds(1));
            for (int i=0; i < 5000; i++){
                log.info("Sent message on Thread: ".concat(Thread.currentThread().getName()));
                kafkaService.sendKafkaMessage("Message", "COMMON-CONSUME")
                        .block();
            }

        }
    }
}
