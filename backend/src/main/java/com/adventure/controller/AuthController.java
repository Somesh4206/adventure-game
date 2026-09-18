package com.adventure.controller;

import com.adventure.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/name")
    public ResponseEntity<?> setName(@RequestBody Map<String, String> body) {
        String username = body.get("name");
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
        }
        Map<String, Object> result = authService.register(username);
        return ResponseEntity.ok(result);
    }
}