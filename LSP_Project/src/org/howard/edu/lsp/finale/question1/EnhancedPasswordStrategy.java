package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password-generation strategy that produces characters from
 * A–Z, a–z, and 0–9 using {@link java.security.SecureRandom}.
 */
public class EnhancedPasswordStrategy implements PasswordGenerationStrategy {

    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive.");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED.length());
            sb.append(ALLOWED.charAt(index));
        }
        return sb.toString();
    }
}

