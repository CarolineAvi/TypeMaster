package com.cori.typemaster.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApplication extends Application {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static Stage primaryStage;
    private static StackPane mainRoot;
    private static Scene mainScene;

    private static boolean isFullScreen = false;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        mainRoot = new StackPane();
        mainScene = new Scene(mainRoot, WIDTH, HEIGHT);

        // Dodaj stylesheet globalnie dla całej aplikacji
        mainScene.getStylesheets().add(getClass().getResource("/com/cori/typemaster/style.css").toExternalForm());

        // Wyłącz systemową obsługę ESC do wychodzenia z pełnego ekranu
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        mainScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                setFullScreen(!isFullScreen());
            }
            // Escape zostawiamy do obsługi tylko dla game-screen przez kontroler
        });

        stage.setScene(mainScene);
        stage.setTitle("TypeMaster");
        stage.setResizable(false);
        stage.show();

        switchScene("/com/cori/typemaster/controller/main-screen.fxml");
    }

    public static void switchScene(String fxmlPath) {
        try {
            if (primaryStage != null) {
                isFullScreen = primaryStage.isFullScreen();
            }
            FXMLLoader loader = new FXMLLoader(GameApplication.class.getResource(fxmlPath));
            Parent newRoot = loader.load();
            mainRoot.getChildren().setAll(newRoot);

            if (isFullScreen) {
                primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                primaryStage.setFullScreen(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        if (primaryStage != null) {
            if (fullScreen) {
                primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            }
            primaryStage.setFullScreen(fullScreen);
        }
    }

    public static boolean isFullScreen() {
        return isFullScreen;
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}