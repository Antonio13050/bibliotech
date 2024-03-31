package com.example.appreactspring.controller;

import com.example.appreactspring.model.User;
import com.example.appreactspring.model.transport.UserResponseDTO;
import com.example.appreactspring.model.transport.operation.create.CreateUserForm;
import com.example.appreactspring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserForm body, UriComponentsBuilder uriComponentsBuilder){
        UserResponseDTO response = this.userService.create(body);
        return ResponseEntity.created(uriComponentsBuilder.path("user/{id}").buildAndExpand(response.id()).toUri()).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> listUsers(){
        List<UserResponseDTO> response = this.userService.listUsers();
        return ResponseEntity.ok(response);
    }



}
