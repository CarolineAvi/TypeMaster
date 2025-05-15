package com.cori.typemaster.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SelectStoryController {

    @FXML
    private ListView<String> storyListView;

    @FXML
    private Button startButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // Przykładowe historie (usuń i załaduj własne, jeśli chcesz)
        storyListView.setItems(FXCollections.observableArrayList(
            "Historia 1", "Historia 2", "Historia 3"
        ));

        // Przyciski
        startButton.setOnAction(e -> startSelectedStory());
        backButton.setOnAction(e -> handleBack());

        // Start można kliknąć dopiero po wybraniu historii
        startButton.disableProperty().bind(
            storyListView.getSelectionModel().selectedItemProperty().isNull()
        );
    }

    @FXML
    private void startSelectedStory() {
        String selectedStory = storyListView.getSelectionModel().getSelectedItem();
        if (selectedStory != null) {
            // Przekaż wybraną historię i przejdź do ekranu gry
            com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/game-screen.fxml");
        }
    }

    @FXML
    private void handleBack() {
        com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml");
    }
}