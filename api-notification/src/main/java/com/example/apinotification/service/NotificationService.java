package com.example.apinotification.service;

import com.example.apinotification.model.Notification;
import com.example.apinotification.model.enums.StatusNotificationEnum;
import com.example.apinotification.model.transport.operation.create.CreateNotificationForm;
import com.example.apinotification.repository.NotificationRepository;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotificationService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final NotificationRepository notificationRepository;

    private final JavaMailSender emailSender;

    public NotificationService(NotificationRepository notificationRepository, JavaMailSender emailSender) {
        this.notificationRepository = notificationRepository;
        this.emailSender = emailSender;
    }

    @Transactional
    public void sendNotification(CreateNotificationForm form){

        String emailText = "Bem-vindo(a), " + form.ownerRef() + "! Seu cadastro foi realizado com sucesso!";

        Notification newNotification = new Notification(form);

        newNotification.setNotificationFrom(emailFrom);
        newNotification.setSubject("Cadastro realizado!");
        newNotification.setText(emailText);
        newNotification.setSendDateNotification(LocalDateTime.now());

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(newNotification.getNotificationFrom());
            message.setTo(newNotification.getNotificationTo());
            message.setSubject(newNotification.getSubject());
            message.setText(newNotification.getText());
            emailSender.send(message);

            newNotification.setStatusNotificationEnum(StatusNotificationEnum.SENT);

        } catch (MailException e){
            newNotification.setStatusNotificationEnum(StatusNotificationEnum.ERROR);

        } finally {
            notificationRepository.save(newNotification);

        }
    }
}
