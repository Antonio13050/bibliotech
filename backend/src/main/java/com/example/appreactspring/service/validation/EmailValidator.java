package com.example.appreactspring.service.validation;

import com.example.appreactspring.exception.UserAlreadyExistsException;
import com.example.appreactspring.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

    private final UserRepository userRepository;

    public EmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void validateUniqueEmail(String email) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + email + ", already exists: ");
        }
    }
}