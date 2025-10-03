package com.example.demo.controller;

import com.example.demo.dto.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password"); 

        long userId = Math.abs(email.hashCode());

        String token = "fake-jwt-token-" + userId;

        LoginResponse response = new LoginResponse(token, userId, email);
        return ResponseEntity.ok(response);
    }
}
