package com.example.passwordmanager;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPRSTUWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+{}";

    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL_CHARS;

    private static final SecureRandom random = new SecureRandom();
    public static String generatePassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }
        return password.toString();
    }
}
