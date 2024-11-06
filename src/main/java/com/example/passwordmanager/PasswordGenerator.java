package com.example.passwordmanager;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPRSTUWXYZ"; // Definiuje stala z dużymi literami
    private static final String LOWER = UPPER.toLowerCase(); // Definiuje stałą z małymi literami
    private static final String DIGITS = "0123456789"; // Definiuje stałą z cyframi
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+{}"; // Definiuje stałą ze znakami specjalnymi

    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL_CHARS; // Łączy wszystkie znaki w jedną stałą aby mieć dostęp do wszystkich podczas generowania hasła

    private static final SecureRandom random = new SecureRandom(); // Tworzy instancję SecureRandom która będzie uzywana do generowania losowych znaków
    public static String generatePassword() { // metoda statyczna która generuje losowe hasło
        StringBuilder password = new StringBuilder(); // tworzy nowy stringbuilder(pozwala łaczyć ze sobą rózne informacje) do przechowywania hasłą
        for (int i = 0; i < 12; i++) { // petłą wykonująca się 12 razy aby stworzyć hasło o tej długości
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length()))); // wybiera losowy znak z ALL_CHARS
        }
        return password.toString(); // zwraca wygenerowane haslo jako string
    }
}
