package com.adventure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/auth/name")
    public ResponseEntity<?> setName(@RequestBody Map<String, String> body) {
        String username = body.get("name");
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name is required"));
        }
        
        String playerId = UUID.randomUUID().toString();
        String token = UUID.randomUUID().toString();
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("player", Map.of(
            "id", playerId,
            "name", username.trim()
        ));
        
        return ResponseEntity.ok(response);
    }
}