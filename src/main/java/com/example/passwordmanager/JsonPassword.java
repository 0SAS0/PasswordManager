package com.example.passwordmanager;

import javafx.scene.control.ChoiceBox;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class JsonPassword {
    public static void passwd(ChoiceBox<String> acType){
        String randomPassword = PasswordGenerator.generatePassword();
        JSONObject obj = new JSONObject();
        obj.put("type", acType.getValue());
        obj.put("password", randomPassword);
        try(FileWriter file = new FileWriter("password.json", true)) {
            file.write(obj.toJSONString() + "\n");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Json file created");
    }
}
