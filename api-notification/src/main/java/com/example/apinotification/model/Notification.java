package com.example.apinotification.model;

import com.example.apinotification.model.enums.StatusNotificationEnum;
import com.example.apinotification.model.transport.operation.create.CreateNotificationForm;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID notificationId;
    private String ownerRef;
    private String notificationFrom;
    private String notificationTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateNotification;
    private StatusNotificationEnum statusNotificationEnum;

    public Notification() {
    }

    public Notification(CreateNotificationForm form) {
        this.ownerRef = form.ownerRef();
        this.notificationTo = form.notificationTo();

    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public String getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(String ownerRef) {
        this.ownerRef = ownerRef;
    }

    public String getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(String notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    public String getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(String notificationTo) {
        this.notificationTo = notificationTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getSendDateNotification() {
        return sendDateNotification;
    }

    public void setSendDateNotification(LocalDateTime sendDateNotification) {
        this.sendDateNotification = sendDateNotification;
    }

    public StatusNotificationEnum getStatusNotificationEnum() {
        return statusNotificationEnum;
    }

    public void setStatusNotificationEnum(StatusNotificationEnum statusNotificationEnum) {
        this.statusNotificationEnum = statusNotificationEnum;
    }
}
