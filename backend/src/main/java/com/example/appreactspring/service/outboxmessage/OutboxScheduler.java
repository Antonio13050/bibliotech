package com.example.appreactspring.service.outboxmessage;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxScheduler {

    private final OutboxMessageService outboxMessageService;

    public OutboxScheduler(OutboxMessageService outboxMessageService) {
        this.outboxMessageService = outboxMessageService;
    }

    @Scheduled(fixedRate = 30000L) //30 segundos
    public void sendPendingScheduler() {
        outboxMessageService.sendTopPendingMessages();
    }
}
