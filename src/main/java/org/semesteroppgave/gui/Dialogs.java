package org.semesteroppgave.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;

public class Dialogs {

    public static void showErrorDialog(String errorTitle, String errorHeader, String errorContent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setHeaderText(errorHeader);
        alert.setContentText(errorContent);

        alert.showAndWait();
    }

    public static void showSuccessDialog(String successTitle, String successHeader, String successContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(successTitle);
        alert.setHeaderText(successHeader);
        alert.setContentText(successContent);

        alert.showAndWait();
    }
}