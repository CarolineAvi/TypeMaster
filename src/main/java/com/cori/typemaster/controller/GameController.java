package com.cori.typemaster.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;

/**
 * Controller for the game screen.
 * <p>
 * Handles story display, typing area, and pause/quit functionality in the game.
 * Provides mechanisms for pausing/resuming the game and transitioning between screens.
 * </p>
 * <h2>Key Features</h2>
 * <ul>
 *     <li>Displays the current story's title and content.</li>
 *     <li>Handles pause overlay, including resume, quit, and main menu navigation.</li>
 *     <li>Captures the ESC key to toggle pause.</li>
 *     <li>Placeholder for end-game logic when the typing is complete.</li>
 * </ul>
 *
 */
public class GameController {

    @FXML
    private Label storyTitleLabel;

    @FXML
    private TextArea storyContentArea;

    @FXML
    private Button quitButton;

    @FXML
    private Button resumeButton;

    @FXML
    private Button toMainMenuButton;

    @FXML
    private VBox pauseOverlay;

    @FXML
    private StackPane rootPane;

    /**
     * Initializes event handlers for the game screen.
     * <ul>
     *     <li>Assigns actions to pause menu buttons.</li>
     *     <li>Initializes the pause overlay in a hidden state.</li>
     *     <li>Captures the ESCAPE key to toggle the pause overlay.</li>
     *     <li>Provides a placeholder for handling story completion.</li>
     * </ul>
     */
    @FXML
    public void initialize() {
        // Handle actions in the pause menu
        resumeButton.setOnAction(e -> setPauseOverlayVisible(false));
        toMainMenuButton.setOnAction(e -> com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml"));
        quitButton.setOnAction(e -> com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml"));

        // Hide the pause overlay at the start
        setPauseOverlayVisible(false);

        // Handle the ESC key for toggling pause overlay only in the game-screen
        pauseOverlay.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                rootPane.requestFocus();
                rootPane.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.ESCAPE) {
                        setPauseOverlayVisible(!pauseOverlay.isVisible());
                        e.consume();
                    }
                });
            }
        });
    }

    /**
     * Shows or hides the pause overlay.
     * @param visible true to show pause overlay; false to hide
     */
    private void setPauseOverlayVisible(boolean visible) {
        pauseOverlay.setVisible(visible);
        pauseOverlay.setMouseTransparent(!visible);
    }
}