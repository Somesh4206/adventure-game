package com.adventure.service;

import com.adventure.model.GameScene;
import com.adventure.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameDataService gameDataService;
    private final ObjectMapper objectMapper;
    
    // In-memory player storage (no database)
    private final Map<String, Player> playerStore = new ConcurrentHashMap<>();

    public GameScene getCurrentScene(String playerId) {
        Player player = getPlayer(playerId);
        GameScene scene = gameDataService.getScene(player.getCurrentSceneId());
        return filterChoicesForPlayer(scene, player);
    }

    public Map<String, Object> makeChoice(String playerId, String choiceId) {
        Player player = getPlayer(playerId);
        GameScene currentScene = gameDataService.getScene(player.getCurrentSceneId());

        GameScene.Choice selectedChoice = currentScene.getChoices().stream()
                .filter(c -> c.getId().equals(choiceId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid choice: " + choiceId));

        // Validate item requirement
        if (selectedChoice.getRequiresItem() != null &&
                !player.getInventory().contains(selectedChoice.getRequiresItem())) {
            throw new IllegalStateException("Missing required item: " + selectedChoice.getRequiresItem());
        }

        // Award score
        player.setTotalScore(player.getTotalScore() + Math.max(0, selectedChoice.getScoreBonus()));
        player.setChoicesMade(player.getChoicesMade() + 1);

        // Track completed scene
        if (!player.getCompletedScenes().contains(player.getCurrentSceneId())) {
            player.getCompletedScenes().add(player.getCurrentSceneId());
        }

        // Advance to next scene
        String nextSceneId = selectedChoice.getNextSceneId();
        player.setCurrentSceneId(nextSceneId);

        // Update chapter
        GameScene nextScene = gameDataService.getScene(nextSceneId);
        if (nextScene != null) {
            player.setCurrentChapter(nextScene.getChapter());
            // Award scene score
            if (nextScene.getScoreValue() > 0) {
                player.setTotalScore(player.getTotalScore() + nextScene.getScoreValue());
            } else if (nextScene.getScoreValue() < 0) {
                player.setHealthPoints(Math.max(0, player.getHealthPoints() + nextScene.getScoreValue()));
            }

            // Add items from scene
            if (nextScene.getItemsAvailable() != null) {
                for (String item : nextScene.getItemsAvailable()) {
                    if (!player.getInventory().contains(item)) {
                        player.getInventory().add(item);
                    }
                }
            }

            // Check achievements
            checkAchievements(player, nextScene);
        }

        player.setLastPlayed(LocalDateTime.now());
        playerStore.put(playerId, player);

        Map<String, Object> result = new HashMap<>();
        result.put("scene", filterChoicesForPlayer(nextScene, player));
        result.put("player", player);
        result.put("scoreEarned", Math.max(0, selectedChoice.getScoreBonus()) + Math.max(0, nextScene != null ? nextScene.getScoreValue() : 0));
        return result;
    }

    public Player resetGame(String playerId) {
        Player player = getPlayer(playerId);
        player.setCurrentSceneId("start");
        player.setCurrentChapter(1);
        player.setTotalScore(0);
        player.setHealthPoints(100);
        player.getInventory().clear();
        player.getCompletedScenes().clear();
        player.setDeaths(player.getDeaths() + 1);
        player.setChoicesMade(0);
        player.setGameFlags("{}");
        player.setLastPlayed(LocalDateTime.now());
        playerStore.put(playerId, player);
        return player;
    }

    // Leaderboard removed - no database persistence

    private GameScene filterChoicesForPlayer(GameScene scene, Player player) {
        if (scene == null) return null;
        // Create a copy with filtered choices
        GameScene filtered = new GameScene();
        filtered.setId(scene.getId());
        filtered.setTitle(scene.getTitle());
        filtered.setNarrative(scene.getNarrative());
        filtered.setAmbiance(scene.getAmbiance());
        filtered.setChapter(scene.getChapter());
        filtered.setItemsAvailable(scene.getItemsAvailable());
        filtered.setEndScene(scene.isEndScene());
        filtered.setEndType(scene.getEndType());
        filtered.setScoreValue(scene.getScoreValue());
        filtered.setEffects(scene.getEffects());

        if (scene.getChoices() != null) {
            List<GameScene.Choice> available = scene.getChoices().stream()
                    .filter(c -> c.getRequiresItem() == null || player.getInventory().contains(c.getRequiresItem()))
                    .toList();
            filtered.setChoices(available);
        }
        return filtered;
    }

    private void checkAchievements(Player player, GameScene scene) {
        List<String> achievements = player.getAchievements();

        if (scene.getId().equals("seal_correct") && !achievements.contains("first_seal")) {
            achievements.add("first_seal");
        }
        if (scene.getId().equals("seal2_correct") && !achievements.contains("second_seal")) {
            achievements.add("second_seal");
        }
        if (scene.getId().equals("ending_good") && !achievements.contains("true_ending")) {
            achievements.add("true_ending");
        }
        if (scene.getId().equals("library_witness") && !achievements.contains("compassionate")) {
            achievements.add("compassionate");
        }
        if (player.getInventory().contains("pale_journal") && !achievements.contains("investigator")) {
            achievements.add("investigator");
        }
        if (player.getTotalScore() >= 200 && !achievements.contains("high_scorer")) {
            achievements.add("high_scorer");
        }
    }

    public Player getPlayerInfo(String playerId) {
        return getPlayer(playerId);
    }

    private Player getPlayer(String playerId) {
        return playerStore.computeIfAbsent(playerId, id -> {
            Player newPlayer = new Player();
            newPlayer.setName("Player");
            return newPlayer;
        });
    }
}