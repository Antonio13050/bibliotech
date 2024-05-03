package com.example.appreactspring.service.notification;

import com.example.appreactspring.model.User;
import com.example.appreactspring.service.outboxmessage.OutboxMessageService;
import com.example.appreactspring.transport.operation.create.CreateNotificationForm;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final OutboxMessageService outboxMessageService;

    public NotificationService(OutboxMessageService outboxMessageService) {
        this.outboxMessageService = outboxMessageService;
    }
    @Transactional
    public void notifyUserCreation(User user) {
        CreateNotificationForm notificationForm = new CreateNotificationForm(user.getUsername(), user.getEmail());
        String destination = "orders.v1.order-created";
        outboxMessageService.store(destination, notificationForm);
    }
}