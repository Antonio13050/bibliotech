package com.example.appreactspring.controller;

import com.example.appreactspring.exception.UserAlreadyExistsException;
import com.example.appreactspring.model.User;
import com.example.appreactspring.model.transport.UserResponseDTO;
import com.example.appreactspring.model.transport.operation.create.CreateUserForm;
import com.example.appreactspring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserForm body, UriComponentsBuilder uriComponentsBuilder) throws UserAlreadyExistsException {
        UserResponseDTO response = this.userService.create(body);
        return ResponseEntity.created(uriComponentsBuilder.path("user/{id}").buildAndExpand(response.id()).toUri()).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> listUsers(){
        List<UserResponseDTO> response = this.userService.listUsers();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
    @RequestMapping(value = "/check-email/{email}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkEmailExists(@PathVariable String email) {
        try {
            userService.validateUniqueEmail(email);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
    @RequestMapping(value = "/check-username/{username}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkUsernameExists(@PathVariable String username) {
        try {
            userService.validateUniqueUsername(username);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
