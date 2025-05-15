package com.cori.typemaster.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;

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

    @FXML
    public void initialize() {
        // Obsługa przycisków w menu
        resumeButton.setOnAction(e -> setPauseOverlayVisible(false));
        toMainMenuButton.setOnAction(e -> com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml"));

        // Obsługa przycisku Quit
        quitButton.setOnAction(e -> com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml"));

        // Schowanie nakładki na start
        setPauseOverlayVisible(false);

        // Obsługa ESC tylko w game-screen!
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

        // --- tutaj przykładowy mechanizm kończenia po wpisaniu wszystkich znaków ---
        // Jeśli masz pole tekstowe do wpisywania, musisz reagować na jego zmiany.
        // Poniżej uproszczenie (trzeba dostosować do Twojej logiki):
        /*
        storyContentArea.textProperty().addListener((obs, oldText, newText) -> {
            if (czyUzytkownikWpisalWszystko()) {
                zakonczGre();
            }
        });
        */
    }

    private void setPauseOverlayVisible(boolean visible) {
        pauseOverlay.setVisible(visible);
        pauseOverlay.setMouseTransparent(!visible);
    }

    // Dodaj tutaj logikę kończenia gry:
    private void zakonczGre() {
        // np. wyświetl podsumowanie lub przejdź do ekranu końcowego
        com.cori.typemaster.app.GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml");
    }
}