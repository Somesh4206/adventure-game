package com.adventure.controller;

import com.adventure.model.Player;
import com.adventure.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/scene")
    public ResponseEntity<?> getCurrentScene(@AuthenticationPrincipal Player player) {
        try {
            return ResponseEntity.ok(gameService.getCurrentScene(player.getId()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/choose")
    public ResponseEntity<?> makeChoice(@AuthenticationPrincipal Player player,
                                         @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(gameService.makeChoice(player.getId(), body.get("choiceId")));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetGame(@AuthenticationPrincipal Player player) {
        return ResponseEntity.ok(gameService.resetGame(player.getId()));
    }

    @GetMapping("/player")
    public ResponseEntity<?> getPlayerInfo(@AuthenticationPrincipal Player player) {
        return ResponseEntity.ok(player);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<?> getLeaderboard() {
        return ResponseEntity.ok(gameService.getLeaderboard());
    }
}
