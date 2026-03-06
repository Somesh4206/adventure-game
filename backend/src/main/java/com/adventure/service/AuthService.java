package com.adventure.service;

import com.adventure.model.Player;
import com.adventure.repository.PlayerRepository;
import com.adventure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Map<String, Object> register(String username, String email, String password) {
        if (playerRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (playerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        Player player = new Player();
        player.setUsername(username);
        player.setEmail(email);
        player.setPassword(passwordEncoder.encode(password));
        Player saved = playerRepository.save(player);

        String token = jwtUtil.generateToken(saved.getId(), saved.getUsername());
        return buildAuthResponse(saved, token);
    }

    public Map<String, Object> login(String username, String password) {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(password, player.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(player.getId(), player.getUsername());
        return buildAuthResponse(player, token);
    }

    private Map<String, Object> buildAuthResponse(Player player, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("player", player);
        return response;
    }
}
