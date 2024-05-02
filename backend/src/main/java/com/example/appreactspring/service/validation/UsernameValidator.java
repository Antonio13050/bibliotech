package com.example.appreactspring.service.validation;

import com.example.appreactspring.exception.UserAlreadyExistsException;
import com.example.appreactspring.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator {

    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateUniqueUsername(String username) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + username + ", already exists: ");
        }
    }
}
