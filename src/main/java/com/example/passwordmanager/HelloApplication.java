package com.example.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException { // metoda start jest wywoływana gdy aplikacja jest uruchamiana
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));//tworzy nowy obiekt fxmloader, ktory laduje plik fxml z zasobów
        Scene scene = new Scene(fxmlLoader.load(), 250, 130);//Tworzy nową scene na podstawie zaladowanego pliku FXML i ustawia jej wymiary
        stage.setTitle("Password Manager"); // Ustawia tytuł okna aplikacji
        stage.setScene(scene); // Przypisuje utworzoną scenę do okna
        stage.show(); // Wyświetla okno aplikacji
    }
    public static void main(String[] args) {
        launch();
    }
}