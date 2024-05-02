package com.example.appreactspring.dto;

import com.example.appreactspring.model.Role;
import com.example.appreactspring.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(UUID id, String username, String email, Set<Role> roles){

    public UserResponseDTO(User user){
        this(user.getUserId(), user.getUsername(), user.getEmail(), new HashSet<>(user.getRoles()));
    }
}
