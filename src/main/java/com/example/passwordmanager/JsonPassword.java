package com.example.passwordmanager;

import javafx.scene.control.ChoiceBox;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class JsonPassword {
    public static void passwd(ChoiceBox<String> acType) {
        String randomPassword = PasswordGenerator.generatePassword();
        JSONObject obj = new JSONObject();
        obj.put("Type", acType.getValue());
        obj.put("Password", randomPassword);
        try {
            FileWriter file = new FileWriter("Password.json");
            file.write(obj.toJSONString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Json file created");
    }
}
