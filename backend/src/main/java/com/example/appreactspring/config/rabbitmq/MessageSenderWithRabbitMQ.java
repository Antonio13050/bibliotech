package com.example.appreactspring.config.rabbitmq;

import com.example.appreactspring.core.MessageSender;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderWithRabbitMQ implements MessageSender {

    private final RabbitTemplate rabbitTemplate;

    public MessageSenderWithRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void send(String destination, String rawContent) {
        rabbitTemplate.send(destination, "", MessageBuilder.withBody(rawContent.getBytes()).build());
    }
}
