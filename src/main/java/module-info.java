module com.example.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.desktop;
    requires java.dotenv;


    opens com.example.passwordmanager to javafx.fxml;
    exports com.example.passwordmanager;
}