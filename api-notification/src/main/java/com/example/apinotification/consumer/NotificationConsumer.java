package com.example.apinotification.consumer;

import com.example.apinotification.model.transport.operation.create.CreateNotificationForm;
import com.example.apinotification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "orders.v1.order-created.send-notification")
    public void onOrderCreated(CreateNotificationForm event) {
        notificationService.sendNotification(event);
        System.out.println("Id recebido " + event.notificationTo());
    }

}
