package com.adventure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(name = "current_scene_id")
    private String currentSceneId = "start";

    @Column(name = "current_chapter")
    private Integer currentChapter = 1;

    @Column(name = "total_score")
    private Integer totalScore = 0;

    @Column(name = "health_points")
    private Integer healthPoints = 100;

    @Column(name = "max_health")
    private Integer maxHealth = 100;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_inventory", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "item")
    private List<String> inventory = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_completed_scenes", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "scene_id")
    private List<String> completedScenes = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_achievements", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "achievement")
    private List<String> achievements = new ArrayList<>();

    @Column(name = "deaths")
    private Integer deaths = 0;

    @Column(name = "choices_made")
    private Integer choicesMade = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "last_played")
    private LocalDateTime lastPlayed = LocalDateTime.now();

    @Column(name = "game_flags", columnDefinition = "TEXT")
    private String gameFlags = "{}";

    @Enumerated(EnumType.STRING)
    private PlayerRole role = PlayerRole.USER;

    public enum PlayerRole {
        USER, ADMIN
    }
}
