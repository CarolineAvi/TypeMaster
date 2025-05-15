package com.cori.typemaster.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainScreenController {

    @FXML
    private Button RandomStoryButton;

    @FXML
    private Button selectStoryButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    @FXML
    private void initialize() {
        RandomStoryButton.setOnAction(this::handleRandomStory);
        selectStoryButton.setOnAction(this::handleSelectStory);
        settingsButton.setOnAction(this::handleSettings);
        exitButton.setOnAction(this::handleExit);
    }

    private void handleRandomStory(ActionEvent event) {
        switchToScreen("/com/cori/typemaster/controller/game-screen.fxml");
    }

    private void handleSelectStory(ActionEvent event) {
        // Przejście do ekranu wyboru historii
        com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/selectStory-screen.fxml");
    }

    private void handleSettings(ActionEvent event) {
        com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/settings-screen.fxml");
    }

    private void handleExit(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void switchToScreen(String fxmlPath) {
        com.cori.typemaster.app.GameApplication.switchScene(fxmlPath);
    }
}