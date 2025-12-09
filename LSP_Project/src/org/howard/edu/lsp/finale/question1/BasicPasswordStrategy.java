package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password-generation strategy that produces digits only (0–9)
 * using {@link java.util.Random}.
 */
public class BasicPasswordStrategy implements PasswordGenerationStrategy {

    private static final String DIGITS = "0123456789";
    private final Random random = new Random();

    @Override
    public String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be positive.");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            sb.append(DIGITS.charAt(index));
        }
        return sb.toString();
    }
}
