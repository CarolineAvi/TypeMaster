package com.cori.typemaster.controller;

import com.cori.typemaster.app.GameApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsController {

    @FXML
    private ComboBox<String> resolutionBox;
    @FXML
    private CheckBox fullscreenToggle;
    @FXML
    private Button applyButton;
    @FXML
    private Button backButton;

    private final String[] resolutions16_9 = {
        "1280 x 720",
        "1366 x 768",
        "1600 x 900",
        "1920 x 1080",
        "2560 x 1440",
        "3200 x 1800",
        "3840 x 2160"
    };

    @FXML
    public void initialize() {
        fullscreenToggle.setSelected(GameApplication.getPrimaryStage().isFullScreen());
        fullscreenToggle.setOnAction(e -> updateResolutionBox(fullscreenToggle.isSelected()));
        updateResolutionBox(fullscreenToggle.isSelected());

        applyButton.setOnAction(e -> applySettings());
        backButton.setOnAction(e -> GameApplication.switchScene("/com/cori/typemaster/controller/main-screen.fxml"));
    }

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