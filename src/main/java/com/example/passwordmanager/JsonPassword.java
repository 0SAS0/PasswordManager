package com.example.passwordmanager;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonPassword {
    static String randomPassword = PasswordGenerator.generatePassword();
    public static void passwd(String acType) {
        JSONObject obj = new JSONObject();
        obj.put("Type", acType);
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
