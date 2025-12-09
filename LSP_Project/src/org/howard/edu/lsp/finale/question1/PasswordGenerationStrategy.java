package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password-generation algorithms.
 */
public interface PasswordGenerationStrategy {
    /**
     * Generates a password of the specified length.
     *
     * @param length desired password length; must be positive
     * @return generated password string
     * @throws IllegalArgumentException if length is not positive
     */
    String generate(int length);
}

