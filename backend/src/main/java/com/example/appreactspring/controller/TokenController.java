package com.example.appreactspring.controller;

import com.example.appreactspring.dto.LoginRequest;
import com.example.appreactspring.dto.LoginResponse;
import com.example.appreactspring.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse response = this.tokenService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

}
