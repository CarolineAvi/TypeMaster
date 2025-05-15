package com.cori.typemaster.model;

public class Story {
    private String title;
    private String content;
    private String difficulty;

    public Story(String title, String content, String difficulty) {
        this.title = title;
        this.content = content;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return title + " (" + difficulty + ")";
    }
}