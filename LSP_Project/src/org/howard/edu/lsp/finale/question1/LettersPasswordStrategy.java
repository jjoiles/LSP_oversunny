package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Password-generation strategy that produces letters only (A–Z, a–z).
 */
public class LettersPasswordStrategy implements PasswordGenerationStrategy {

    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive.");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(index));
        }
        return sb.toString();
    }
}
