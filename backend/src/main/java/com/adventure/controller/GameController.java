package com.adventure.controller;

import com.adventure.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/scene")
    public ResponseEntity<?> getCurrentScene(@RequestParam String playerId) {
        try {
            return ResponseEntity.ok(gameService.getCurrentScene(playerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/choose")
    public ResponseEntity<?> makeChoice(@RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(gameService.makeChoice(
                    body.get("playerId"),
                    body.get("choiceId")
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetGame(@RequestParam String playerId) {
        try {
            return ResponseEntity.ok(gameService.resetGame(playerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/player")
    public ResponseEntity<?> getPlayerInfo(@RequestParam String playerId) {
        try {
            return ResponseEntity.ok(gameService.getPlayerInfo(playerId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    // Leaderboard endpoint removed - no database persistence
}