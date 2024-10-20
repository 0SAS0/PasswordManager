package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ChoiceBox<String> acType;
    @FXML
    protected void CpPassword() {
        String randomPassword = PasswordGenerator.generatePassword();
        System.out.println(randomPassword);
        JsonPassword.passwd(acType);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acType.getItems().addAll("Steam", "Facebook", "Twitter","Google","Other");
    }
}
