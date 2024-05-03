package com.example.appreactspring.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_outbox_message")
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String destination;

    @Column(columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private int tentatives;

    public void increaseTentatives() {
        tentatives++;
    }

    public enum Status {
        PENDING, SENT, ERROR;
    }

    public OutboxMessage() {
    }

    public OutboxMessage(String destination, String content) {
        this.destination = destination;
        this.content = content;
    }

    public String getDestination() {
        return destination;
    }

    public String getContent() {
        return content;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTentatives() {
        return tentatives;
    }
}