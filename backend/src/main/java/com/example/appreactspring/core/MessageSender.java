package com.example.appreactspring.core;

public interface MessageSender {
    void send(String destination, String rawContent);
}