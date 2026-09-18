package com.adventure.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private String name;

    private String currentSceneId = "start";

    private Integer currentChapter = 1;

    private Integer totalScore = 0;

    private Integer healthPoints = 100;

    private Integer maxHealth = 100;

    private java.util.List<String> inventory = new java.util.ArrayList<>();

    private java.util.List<String> completedScenes = new java.util.ArrayList<>();

    private java.util.List<String> achievements = new java.util.ArrayList<>();

    private Integer deaths = 0;

    private Integer choicesMade = 0;

    private String gameFlags = "{}";

}