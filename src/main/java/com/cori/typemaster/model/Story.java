package com.cori.typemaster.model;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a single story entity within the TypeMaster application.
 * <p>
 * A story consists of a title, content, and a difficulty level.
 * Instances of this class are usually loaded from JSON resources through the {@link com.cori.typemaster.service.StoryService}.
 * </p>
 *
 * <h2>Main Responsibilities</h2>
 * <ul>
 *     <li>Encapsulates the minimal properties of a story for display and gameplay.</li>
 *     <li>Serves as a data model for the story list and selection UIs.</li>
 * </ul>
 *
 * <h3>Example usage</h3>
 * <pre>
 *     Story story = new Story("Adventure", "Once upon a time...", "easy");
 * </pre>
 *
 * <h3>Fields</h3>
 * <ul>
 *   <li>{@link #title} – Story title for display and identification.</li>
 *   <li>{@link #content} – The text content to be typed or read.</li>
 *   <li>{@link #difficulty} – Difficulty label (e.g., "easy", "medium", "hard").</li>
 * </ul>
 * <p>
 * Instances are typically immutable; modify only via new instantiation.
 *
 * @param title      The story's title.
 * @param content    The main content of the story (to be typed by the player).
 * @param difficulty The difficulty level of the story (e.g., easy, medium, hard).
 */
public record Story(String title, String content, String difficulty) {
    /**
     * Constructs a new story with the given title, content, and difficulty.
     *
     * @param title      Display title of the story
     * @param content    Full story text
     * @param difficulty Difficulty label/category
     */
    public Story {
    }

    /**
     * @return the story's title
     */
    @Override
    public String title() {
        return title;
    }

    /**
     * @return the story's content for gameplay/reading
     */
    @Override
    public String content() {
        return content;
    }

    /**
     * @return the story's difficulty label
     */
    @Override
    public String difficulty() {
        return difficulty;
    }

    /**
     * Provides a readable string representation, typically used as the display label.
     *
     * @return title of the story
     */
    @NotNull
    @Override
    public String toString() {
        return title;
    }
}