package com.adventure.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameScene {
    private String id;
    private String title;
    private String narrative;
    private String ambiance; // background mood: dark_forest, ancient_temple, etc.
    private int chapter;
    private List<Choice> choices;
    private List<String> itemsAvailable;
    private boolean isEndScene;
    private String endType; // "victory", "death", "chapter_end"
    private int scoreValue;
    private Map<String, String> effects; // hp_change, item_add, flag_set, etc.

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private String id;
        private String text;
        private String nextSceneId;
        private String requiresItem;
        private String requiresFlag;
        private Map<String, String> effects; // consequences of this choice
        private boolean isDangerous;
        private int scoreBonus;
    }
}
