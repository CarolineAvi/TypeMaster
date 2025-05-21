package com.cori.typemaster.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main application class for TypeMaster.
 * <p>
 * Sets up the primary stage, manages global scene switching,
 * handles fullscreen functionality and integrates a global stylesheet.
 * </p>
 * <ul>
 *   <li>Supports switching between FXML scenes inside a single StackPane root.</li>
 *   <li>Manages application-wide fullscreen toggling (using F11 as override).</li>
 *   <li>Loads and applies a global CSS stylesheet to the entire scene graph.</li>
 *   <li>Prevents system ESC from accidentally exiting fullscreen—handled inside GameController instead.</li>
 * </ul>
 * <strong>Public API:</strong> 
 * <ul>
 *   <li>{@link #switchScene(String)}</li>
 *   <li>{@link #setFullScreen(boolean)}</li>
 *   <li>{@link #isFullScreen()}</li>
 *   <li>{@link #getPrimaryStage()}</li>
 * </ul>
 *
 * @author <a href="https://github.com/CarolineAvi">Caroline</a>
 */
public class GameApplication extends Application {

    /** Default application window width. */
    private static final int WIDTH = 1280;
    /** Default application window height. */
    private static final int HEIGHT = 720;

    /** The main application stage instance. */
    private static Stage primaryStage;
    /** The persistent StackPane root for all scene switchings. */
    private static StackPane mainRoot;

    /** Stores the current fullscreen state. */
    private static boolean isFullScreen = false;

    /**
     * Called by JavaFX upon application start.
     * Initializes the stage, scene, root node, stylesheet and global key handling.
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        mainRoot = new StackPane();
        /* The main Scene object for the application. */
        Scene mainScene = new Scene(mainRoot, WIDTH, HEIGHT);

        // Add a global stylesheet for the entire application
        mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/cori/typemaster/style.css")).toExternalForm());

        // Disable the default ESC behavior for exiting fullscreen
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        // Add global key handler: F11 toggles fullscreen
        mainScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                setFullScreen(!isFullScreen());
            }
            // ESC is reserved for game-screen; handled in the game screen controller
        });

        stage.setScene(mainScene);
        stage.setTitle("TypeMaster");
        stage.setResizable(false);
        stage.show();

        // Load the main screen at startup
        switchScene("/com/cori/typemaster/controller/main-screen.fxml");
    }

    /**
     * Replaces the contents of the main root StackPane with a new FXML view.
     * Maintains a fullscreen state when switching and prevents accidental exit via ESC.
     *
     * @param fxmlPath Absolute resource path to the desired FXML file.
     */
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

    /**
     * Returns the main application stage.
     * Useful for controllers that need window-level properties or actions.
     *
     * @return the primary Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Enables or disables fullscreen mode application-wide.
     * Updates the internal fullscreen state and the actual window.
     *
     * @param fullScreen true to enable fullscreen, false to disable
     */
    public static void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        if (primaryStage != null) {
            if (fullScreen) {
                primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            }
            primaryStage.setFullScreen(fullScreen);
        }
    }

    /**
     * Returns whether the application is currently in fullscreen mode.
     *
     * @return true if fullscreen, false otherwise
     */
    public static boolean isFullScreen() {
        return isFullScreen;
    }

    /**
     * Application entry point for Java launchers.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}