package com.example.appreactspring.service.notification;

import com.example.appreactspring.model.User;
import com.example.appreactspring.transport.operation.create.CreateNotificationForm;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Transactional
    public void notifyUserCreation(User user) {
        CreateNotificationForm event = new CreateNotificationForm(user.getUsername(), user.getEmail());
        rabbitTemplate.convertAndSend("orders.v1.order-created", "", event);
    }
}