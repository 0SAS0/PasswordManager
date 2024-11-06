package com.example.passwordmanager;

import javafx.scene.control.ChoiceBox;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import io.github.cdimascio.dotenv.Dotenv;

public class JsonPassword {
    public static void passwd(ChoiceBox<String> acType, String userKey){ // Metoda passwd zarządza hasłami: zapisuje nowe lub kopiuje do schowka jeśli istnieje
        Dotenv dotenv = Dotenv.load(); // Wczytywanie klucza AES z pliku .env
        String AES_KEY = dotenv.get("AES_KEY");
        String randomPassword = PasswordGenerator.generatePassword(); // Przypisanie do zmiennej funkcji generowania hasła
        JSONObject obj = readPasswords(); // Wczytywanie wszystkie zapisanych haseł z pliku JSON

        String type = acType.getValue(); // Pobranie wybrangeo przez użytkownika typu konta
        if (obj.containsKey(type) && !obj.get(type).toString().isEmpty()){ //Sprawdzanie czy hasło dla danego konta istnieje
            try {
                String decryptedPassword = decrypt(obj.get(type).toString(), userKey);  // Pobranie zaszyfrowanego hasla z JSON i próa odszyfrowania za pomocą klucza
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(new StringSelection(decryptedPassword), null); // Skopiowanie odszyfrowanego hasła do schowka
            }catch (Exception e){
                System.out.println("Niepoprawny klucz AES"); // wyświetlenie blędu w przypadku nieprawdiłowego klucza AES
            }
            return; // zakończenie metody, ponieważ hasło zostało skopiowane
        }
        // Jeżeli hasło nie istnieje, generowanie nowego i szyfrowanie
        try {
            String encryptedPassword = encrypt(randomPassword, AES_KEY); // szyfrowanie nowo wygenerowanego hasła
            obj.put(type, encryptedPassword); //Zapisanie zaszyfrowanego hasła w obiekcie JSON
            try (FileWriter file = new FileWriter("password.json", false)){ // Zapisanie całego obiektu JSON do pliku password.json
                file.write(obj.toJSONString() + "\n");
                file.flush();
            } catch (IOException e) {
                e.printStackTrace(); // Obsługa błędu zapisu do pliku
            }
        } catch (Exception e) {
            e.printStackTrace(); // Obsługa błędów
        }
    }

    public static JSONObject readPasswords(){ // Metoda readPassword wczytuje wszystkie zapisane hasła z pliku JSON
        JSONObject obj = new JSONObject();
        try{
            Object o = new JSONParser().parse(new FileReader("password.json")); // Parsowanie pliku JSON do obiektu JSON
            obj = (JSONObject) o; // Konwersja na JSONObject
        }catch(IOException | ParseException e){
            e.printStackTrace(); // Obsługa błędów
        }

        return obj; // Zwraca obiekt JSON zawierający wszystkie zapisane hasła
    }
    public static String encrypt(String plainText, String key) throws Exception { // Metoda encrypt szyfruje tekst zwykły przy użyciu klucza AES i zwraca zaszyfrowany tekst
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES"); // Tworzy obiekt klucza AES z podanego klucza
        Cipher cipher = Cipher.getInstance("AES"); // Tworzy instancje szyforwania AES
        cipher.init(Cipher.ENCRYPT_MODE,secretKey); // Inicjalizuje szyfrowanie
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes()); // Szyfruje dane i konwertuje wynik na Base64
        return Base64.getEncoder().encodeToString(encryptedBytes); // Zwraca zaszyfrowany tekst

    }
    public static String decrypt(String encryptedText, String key) throws Exception { // Metoda decrypt odszyfrowuje zaszyfrowany tekst używając klucza AES
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES"); // Tworzy obiekt klucza AES z podanego klucza
        Cipher cipher = Cipher.getInstance("AES");// Tworzy instancje szyforwania AES
        cipher.init(Cipher.DECRYPT_MODE,secretKey);// Inicjalizuje odszyfrowanie
        byte [] encryptedBytes = Base64.getDecoder().decode(encryptedText); // Dekodowanie z Base64
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes); // Odszyfrowanie
        return new String(decryptedBytes);
    }
}
