package com.example.appreactspring.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String mesage) {
        super(mesage);
    }
}
