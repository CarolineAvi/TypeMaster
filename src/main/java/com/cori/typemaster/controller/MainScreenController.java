package com.cori.typemaster.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the main menu screen in the TypeMaster application.
 * <p>
 * Handles user interaction with the main menu, including navigating to play a random story,
 * selecting a story, opening settings, and exiting the application.
 * </p>
 * <h2>Main Responsibilities</h2>
 * <ul>
 *     <li>Initializes button actions for main menu navigation.</li>
 *     <li>Delegates scene switching to {@code GameApplication} utility methods.</li>
 *     <li>Handles application window closure via the exit button.</li>
 * </ul>
 *
 * <h3>FXML Fields</h3>
 * <ul>
 *     <li>{@link #RandomStoryButton}</li>
 *     <li>{@link #selectStoryButton}</li>
 *     <li>{@link #settingsButton}</li>
 *     <li>{@link #exitButton}</li>
 * </ul>
 *
 * Usage: Automatically instantiated and managed via FXML loader by JavaFX.
 *
 */
public class MainScreenController {

    /** Button to start a game with a random story. */
    @FXML
    private Button RandomStoryButton;

    /** Button to allow the user to select a specific story. */
    @FXML
    private Button selectStoryButton;

    /** Button to open application settings. */
    @FXML
    private Button settingsButton;

    /** Button to close and exit the application. */
    @FXML
    private Button exitButton;

    /**
     * Called automatically by JavaFX after the FXML fields are injected.
     * Sets up event handlers for all navigation buttons.
     */
    @FXML
    private void initialize() {
        RandomStoryButton.setOnAction(this::handleRandomStory);
        selectStoryButton.setOnAction(this::handleSelectStory);
        settingsButton.setOnAction(this::handleSettings);
        exitButton.setOnAction(this::handleExit);
    }

    /**
     * Handles action for starting a random story game.
     * Navigates to the Game Screen view.
     *
     * @param event The triggered action event.
     */
    private void handleRandomStory(ActionEvent event) {
        switchToScreen("/com/cori/typemaster/controller/game-screen.fxml");
    }

    /**
     * Handles action for selecting a story.
     * Navigates to the Story Selection screen.
     *
     * @param event The triggered action event.
     */
    private void handleSelectStory(ActionEvent event) {
        // Go to the story selection screen
        switchToScreen("/com/cori/typemaster/controller/selectStory-screen.fxml");
    }

    /**
     * Handles action to open the settings screen.
     *
     * @param event The triggered action event.
     */
    private void handleSettings(ActionEvent event) {
        switchToScreen("/com/cori/typemaster/controller/settings-screen.fxml");
    }

    /**
     * Handles application exit. Closes the current JavaFX stage.
     *
     * @param event The triggered action event.
     */
    private void handleExit(ActionEvent event) {
        // Close the main application window
        javafx.stage.Stage stage = (javafx.stage.Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Helper method to switch the main view by loading the specified FXML.
     *
     * @param fxmlPath Absolute resource path to the target FXML file.
     */
    private void switchToScreen(String fxmlPath) {
        com.cori.typemaster.app.GameApplication.switchScene(fxmlPath);
    }
}