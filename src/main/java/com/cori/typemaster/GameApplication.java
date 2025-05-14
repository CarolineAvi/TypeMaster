package com.cori.typemaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class GameApplication extends Application {

    private static final int SCENE_WIDTH = 1280;
    private static final int SCENE_HEIGHT = 720;
    private static final String APP_TITLE = "TypeMaster";
    private static final String MAIN_SCREEN_FXML = "main-screen.fxml";

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle(APP_TITLE);

        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource(MAIN_SCREEN_FXML));
        Scene scene = new Scene(fxmlLoader.load(), SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Ustawienie domyślnego trybu pełnoekranowego

        // Obsługa F11 do włączania/wyłączania pełnoekranowego
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                toggleFullScreen(primaryStage);
            }
        });

        primaryStage.show();
    }

    // Metoda pomocnicza do przełączania trybu pełnoekranowego
    private void toggleFullScreen(Stage stage) {
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
            stage.setWidth(SCENE_WIDTH);
            stage.setHeight(SCENE_HEIGHT);
        } else {
            stage.setFullScreen(true);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}