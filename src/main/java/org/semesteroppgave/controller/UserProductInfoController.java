package org.semesteroppgave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;

import java.io.IOException;

public class UserProductInfoController implements ApplicationController {

    @FXML
    private TextArea txtInfo;

    @Override
    public void initialize() {
        txtInfo.setText(ApplicationData.getInstance().getRegisterProduct().getSelectedProduct().toString());
    }

    @FXML
    private void btnBack() throws IOException {
        Main.setRoot("configuredproduct");
    }

}
