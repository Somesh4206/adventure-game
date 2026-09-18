package com.adventure.repository;

import com.adventure.model.Player;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PlayerRepository {

    private final Map<Long, Player> playerStore = new HashMap<>();
    private long nextId = 1;

    public Player save(Player player) {
        if (player.getName() == null || player.getName().isEmpty()) {
            player.setName("Archivist");
        }
        player.setId(nextId++);
        playerStore.put(player.getId(), player);
        return player;
    }

    public Optional<Player> findById(Long id) {
        return Optional.ofNullable(playerStore.get(id));
    }

    public Optional<Player> findByUsername(String username) {
        return playerStore.values().stream()
                .filter(p -> p.getName() != null && p.getName().equals(username))
                .findFirst();
    }

    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public Iterable<Player> findAll() {
        return playerStore.values();
    }

    public void deleteById(Long id) {
        playerStore.remove(id);
    }
}