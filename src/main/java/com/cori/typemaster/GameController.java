package com.cori.typemaster;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class GameController {
    @FXML
    private Label StartButton;

    @FXML
    private Label ExitButton;

    @FXML
    protected void onStartButtonClick() {
        StartButton.setText("Welcome to TypeMaster!");
    }

    @FXML
    protected void setExitButtonClick() {
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }
}