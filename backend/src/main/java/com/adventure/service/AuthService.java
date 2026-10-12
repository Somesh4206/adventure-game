package com.adventure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    public Map<String, Object> register(String username) {
        String playerId = UUID.randomUUID().toString();
        String token = UUID.randomUUID().toString();
        return buildAuthResponse(playerId, username, token);
    }

    public Map<String, Object> login(String username) {
        String playerId = UUID.randomUUID().toString();
        String token = UUID.randomUUID().toString();
        return buildAuthResponse(playerId, username, token);
    }

    private Map<String, Object> buildAuthResponse(String playerId, String username, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("player", Map.of(
            "id", playerId,
            "name", username
        ));
        return response;
    }
}