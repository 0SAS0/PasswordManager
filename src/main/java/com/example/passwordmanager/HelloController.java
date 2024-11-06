package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public PasswordField typeKey; // Pole typeKey typu PasswordField umożliwia użytkownikowi wporwadzić klucz AES
    @FXML
    private ChoiceBox<String> acType; // Pole acType to element ChoiceBox zawierający listę typów kont

    @FXML
    protected void CpPassword(){ // jest to metoda wywoływana gdy użytkownik naciśnie przycisk "Copy Password"
        String userKey = typeKey.getText(); //Pobieranie klucza AES wpisanego przez użytkownika
        PasswordGenerator.generatePassword(); // Generowanie nowego hasła przy użyciu klasy PasswordGenerator
        JsonPassword.passwd(acType, userKey); // Wywołanie metody passwd z klasy JsonPassword, przekazujac typ konta i klucz AES wpisany wczesniej
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) { // metoda dodaje elementy do choiceboxa
        acType.getItems().addAll("Steam", "Facebook", "Twitter","Google","Other");// dodaje dostępne typy kont do "acType"
    }
}
