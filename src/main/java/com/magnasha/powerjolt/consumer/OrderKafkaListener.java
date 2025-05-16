package com.magnasha.powerjolt.consumer;

import com.magnasha.powerjolt.dto.IntegrationContext;
import com.magnasha.powerjolt.services.IntegrationOrderAcknowledgementService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class OrderKafkaListener {

    private final IntegrationOrderAcknowledgementService integrationOrderAcknowledgementService;

    public OrderKafkaListener(IntegrationOrderAcknowledgementService integrationOrderAcknowledgementService) {
        this.integrationOrderAcknowledgementService = integrationOrderAcknowledgementService;
    }

    //@KafkaListener(topics = "${kafka.topic}", groupId = "order-group")
    public void listen(String message) {
        long startTime = System.nanoTime();
        String correlationId = UUID.randomUUID().toString();
//        log.info("Received message with CorrelationID: {}", correlationId);
        final IntegrationContext context = new IntegrationContext();
        context.setPayload(message);
        context.setCorrelationId(correlationId);
        context.setReceivedAt(Instant.now());
        integrationOrderAcknowledgementService.process(context);
        long durationNanos = System.nanoTime() - startTime;
        double durationMillis = durationNanos / 1_000_000.0;
        double durationSeconds = durationNanos / 1_000_000_000.0;
        //logger.info("{} executed in {:.3f} ms or ({:.6f} seconds)",methodName, durationMillis, durationSeconds);
    }
}
