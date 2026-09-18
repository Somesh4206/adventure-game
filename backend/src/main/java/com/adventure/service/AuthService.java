package com.adventure.service;

import com.adventure.model.Player;
import com.adventure.repository.PlayerRepository;
import com.adventure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PlayerRepository playerRepository;
    private final JwtUtil jwtUtil;

    public Map<String, Object> register(String username) {
        Player player = new Player();
        player.setName(username);
        Player saved = playerRepository.save(player);

        String token = jwtUtil.generateToken(saved.getId(), saved.getName());
        return buildAuthResponse(saved, token);
    }

    public Map<String, Object> login(String username) {
        Player player = playerRepository.findByUsername(username)
                .orElseGet(() -> {
                    Player p = new Player();
                    p.setName(username);
                    return playerRepository.save(p);
                });

        String token = jwtUtil.generateToken(player.getId(), player.getName());
        return buildAuthResponse(player, token);
    }

    private Map<String, Object> buildAuthResponse(Player player, String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("player", player);
        return response;
    }
}