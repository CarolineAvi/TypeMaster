package com.cori.typemaster.controller;

import com.cori.typemaster.app.GameApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the "Settings" screen of the TypeMaster application.
 * <p>
 * Provides UI and logic for viewing and adjusting display settings,
 * specifically the window resolution and fullscreen mode.
 * </p>
 *
 * <h2>Main Responsibilities</h2>
 * <ul>
 *     <li>Allows users to toggle between fullscreen and windowed mode.</li>
 *     <li>Lets users choose a screen resolution when not in fullscreen.</li>
 *     <li>Applies the selected display settings to the main application window.</li>
 *     <li>Facilitates navigation back to the main menu.</li>
 * </ul>
 *
 * <h3>FXML Fields</h3>
 * <ul>
 *     <li>{@link #resolutionBox} – Dropdown for selecting resolution.</li>
 *     <li>{@link #fullscreenToggle} – Checkbox to enable fullscreen mode.</li>
 *     <li>{@link #applyButton} – Button to confirm and apply chosen settings.</li>
 *     <li>{@link #backButton} – Button to return to the main menu screen.</li>
 * </ul>
 *
 * <h3>Usage</h3>
 * <ul>
 *     <li>Instantiated and managed by the JavaFX FXML loader.</li>
 *     <li>Depends on {@link GameApplication#getPrimaryStage()} for stage references.</li>
 * </ul>
 *
 */
public class SettingsController {

    /** ComboBox for selecting the screen resolution. */
    @FXML
    private ComboBox<String> resolutionBox;

    /** Checkbox for toggling fullscreen mode. */
    @FXML
    private CheckBox fullscreenToggle;

    /** Button to apply and save the chosen settings. */
    @FXML
    private Button applyButton;

    /** Button to return to the main menu without applying changes. */
    @FXML
    private Button backButton;

    /** List of common 16:9 screen resolutions for selection (used in windowed mode). */
    private final String[] resolutions16_9 = {
        "1280 x 720",
        "1366 x 768",
        "1600 x 900",
        "1920 x 1080",
        "2560 x 1440",
        "3200 x 1800",
        "3840 x 2160"
    };

    /**
     * Initializes the settings screen after FXML fields are injected.
     * <ul>
     *     <li>Initializes fullscreen toggle and resolution options.</li>
     *     <li>Sets up event handlers for buttons and controls.</li>
     * </ul>
     */
    @FXML
    public void initialize() {
        // Initialize fullscreen checkbox from current stage
        fullscreenToggle.setSelected(GameApplication.getPrimaryStage().isFullScreen());
        fullscreenToggle.setOnAction(e -> updateResolutionBox(fullscreenToggle.isSelected()));
        updateResolutionBox(fullscreenToggle.isSelected());

        // Attach handlers for action buttons
        applyButton.setOnAction(e -> applySettings());
        backButton.setOnAction(e -> GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml"));
    }

    /**
     * Updates the available resolutions based on fullscreen toggle.
     * <ul>
     *     <li>If fullscreen is selected, disables resolution selection and shows only the native resolution.</li>
     *     <li>If windowed, enables resolution selection and lists standard 16:9 resolutions (excluding native).</li>
     * </ul>
     *
     * @param fullscreen whether the window is set to fullscreen
     */
    private void updateResolutionBox(boolean fullscreen) {
        int nativeWidth = (int) Screen.getPrimary().getBounds().getWidth();
        int nativeHeight = (int) Screen.getPrimary().getBounds().getHeight();
        String nativeRes = nativeWidth + " x " + nativeHeight;

        if (fullscreen) {
            resolutionBox.setDisable(true);
            resolutionBox.setItems(FXCollections.observableArrayList(nativeRes));
            resolutionBox.getSelectionModel().selectFirst();
        } else {
            List<String> resList = new ArrayList<>();
            for (String res : resolutions16_9) {
                if (!res.equals(nativeRes)) {
                    resList.add(res);
                }
            }
            resolutionBox.setDisable(false);
            resolutionBox.setItems(FXCollections.observableArrayList(resList));
            resolutionBox.getSelectionModel().selectFirst();
        }
    }

    /**
     * Applies the selected display settings to the main application window.
     * <ul>
     *     <li>Sets the window to fullscreen or windowed mode based on user selection.</li>
     *     <li>If windowed, applies the chosen resolution to the stage size.</li>
     *     <li>Makes the stage non-resizable when in fullscreen.</li>
     * </ul>
     */
    private void applySettings() {
        String res = resolutionBox.getSelectionModel().getSelectedItem();
        boolean fullscreen = fullscreenToggle.isSelected();

        int selectedWidth = 1280;
        int selectedHeight = 720;

        if (res != null) {
            String[] parts = res.split("x");
            selectedWidth = Integer.parseInt(parts[0].trim());
            selectedHeight = Integer.parseInt(parts[1].trim());
        }

        Stage stage = GameApplication.getPrimaryStage();
        stage.setFullScreen(fullscreen);
        stage.setResizable(!fullscreen);

        if (!fullscreen) {
            stage.setWidth(selectedWidth);
            stage.setHeight(selectedHeight);
        }
    }
}