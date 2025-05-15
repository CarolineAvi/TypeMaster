package com.cori.typemaster.controller;

import com.cori.typemaster.model.Story;
import com.cori.typemaster.service.StoryService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Controller for the "Select Story" screen of the TypeMaster application.
 * <p>
 * Presents a list of available stories to the user, allows the user to pick one,
 * and handles navigation to start the selected story or return to the main menu.
 * </p>
 *
 * <h2>Main Responsibilities</h2>
 * <ul>
 *     <li>Loads available stories into a {@link ListView}.</li>
 *     <li>Enables the "Start" button only when a story is selected.</li>
 *     <li>Handles user actions for starting a story or going back to the main menu.</li>
 * </ul>
 *
 * <h3>FXML Fields</h3>
 * <ul>
 *     <li>{@link #storyListView} - displays all available stories.</li>
 *     <li>{@link #startButton} - starts the selected story.</li>
 *     <li>{@link #backButton} - returns to the main screen.</li>
 * </ul>
 *
 * <h3>Notes</h3>
 * <ul>
 *     <li>Currently, the selected story is not directly passed to the game screen;
 *         integration is marked as a TODO.</li>
 *     <li>This controller is managed and instantiated by the JavaFX FXML loader.</li>
 * </ul>
 *
 */
public class SelectStoryController {

    /** The ListView showing all available stories for selection. */
    @FXML
    private ListView<Story> storyListView;

    /** The button to begin playing the selected story. */
    @FXML
    private Button startButton;

    /** The button to return to the main menu without starting a story. */
    @FXML
    private Button backButton;

    /** Holds the list of stories loaded from the story service. */
    private List<Story> storyList;

    /**
     * Initializes the Select Story screen after all FXML elements are injected.
     * <ul>
     *     <li>Loads stories from {@link StoryService} and displays them in the list view.</li>
     *     <li>Sets button actions for starting a story or going back.</li>
     *     <li>Enables the "Start" button only if a selection exists.</li>
     * </ul>
     */
    @FXML
    public void initialize() {
        // Load the list of stories from the service
        StoryService storyService = new StoryService();
        storyList = storyService.loadAllStories();
        storyListView.setItems(FXCollections.observableArrayList(storyList));

        // Set button actions
        startButton.setOnAction(e -> startSelectedStory());
        backButton.setOnAction(e -> handleBack());

        // "Start" button is only enabled after user selects a story
        startButton.disableProperty().bind(
            storyListView.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    /**
     * Starts the game with the currently selected story.
     * <p>
     * <b>Note:</b> Passing the actual selected story to the game screen
     * is a potential extension (see TODO).
     * </p>
     */
    @FXML
    private void startSelectedStory() {
        Story selectedStory = storyListView.getSelectionModel().getSelectedItem();
        if (selectedStory != null) {
            // TODO: Pass selected story to GameController or GameScreen (singleton, context, etc.)
            com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/game-screen.fxml");
        }
    }

    /**
     * Handles the action to return to the main menu screen.
     */
    @FXML
    private void handleBack() {
        com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml");
    }
}