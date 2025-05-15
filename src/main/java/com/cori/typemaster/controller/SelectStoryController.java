package com.cori.typemaster.controller;

import com.cori.typemaster.model.Story;
import com.cori.typemaster.service.StoryService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

public class SelectStoryController {

    @FXML
    private ListView<Story> storyListView;

    @FXML
    private Button startButton;

    @FXML
    private Button backButton;

    private List<Story> storyList;

    @FXML
    public void initialize() {
        // Załaduj listę historii z serwisu
        StoryService storyService = new StoryService();
        storyList = storyService.loadAllStories();
        storyListView.setItems(FXCollections.observableArrayList(storyList));

        // Obsługa przycisków
        startButton.setOnAction(e -> startSelectedStory());
        backButton.setOnAction(e -> handleBack());

        // Przycisk aktywny tylko po wybraniu historii
        startButton.disableProperty().bind(
            storyListView.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void startSelectedStory() {
        Story selectedStory = storyListView.getSelectionModel().getSelectedItem();
        if (selectedStory != null) {
            // TODO: Przekaż wybraną historię do GameController/GameScreen (np. przez singleton, context lub custom load)
            com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/game-screen.fxml");
        }
    }

    @FXML
    private void handleBack() {
        com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml");
    }
}