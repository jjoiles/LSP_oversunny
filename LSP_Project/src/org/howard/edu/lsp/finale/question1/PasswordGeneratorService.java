package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Central service for password generation.
 * <p>
 * This class is a Singleton and also acts as the context for the
 * Strategy pattern: it delegates password generation to the currently
 * selected {@link PasswordGenerationStrategy}.
 * </p>
 */
public class PasswordGeneratorService {

    // Algorithm names (public API expects these string keys)
    private static final String BASIC = "basic";
    private static final String ENHANCED = "enhanced";
    private static final String LETTERS = "letters";

    // Singleton instance
    private static PasswordGeneratorService instance;

    // Current strategy selected at runtime
    private PasswordGenerationStrategy currentStrategy;

    // Registry of available algorithms
    private final Map<String, PasswordGenerationStrategy> algorithms = new HashMap<>();

    /**
     * Private constructor (Singleton).
     * Initializes the available password-generation strategies.
     */
    private PasswordGeneratorService() {
        algorithms.put(BASIC, new BasicPasswordStrategy());
        algorithms.put(ENHANCED, new EnhancedPasswordStrategy());
        algorithms.put(LETTERS, new LettersPasswordStrategy());
    }

    /**
     * Returns the single shared instance of this service (Singleton access point).
     *
     * @return the shared {@code PasswordGeneratorService} instance
     */
    public static synchronized PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * Selects the password-generation algorithm by name.
     *
     * @param name algorithm name: "basic", "enhanced", or "letters"
     * @throws IllegalArgumentException if the name is null or unsupported
     */
    public synchronized void setAlgorithm(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Algorithm name must not be null.");
        }

        // Make lookup case-insensitive, but keep the public names as given
        String normalized = name.toLowerCase(Locale.ROOT);

        PasswordGenerationStrategy strategy = algorithms.get(normalized);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }

        currentStrategy = strategy;
    }

    /**
     * Generates a password using the currently selected algorithm.
     *
     * @param length desired password length; must be positive
     * @return generated password
     * @throws IllegalStateException    if no algorithm has been selected
     * @throws IllegalArgumentException if the length is invalid
     */
    public synchronized String generatePassword(int length) {
        if (currentStrategy == null) {
            throw new IllegalStateException(
                    "No password-generation algorithm has been selected.");
        }
        return currentStrategy.generate(length);
    }

    /**
     * Package-private helper used by tests to clear the currently selected algorithm.
     * This does not change the available algorithm registry.
     */
    void clearAlgorithmForTesting() {
        this.currentStrategy = null;
    }

    /*
     * === Design Pattern Documentation ===
     *
     * Patterns used:
     *
     * 1. Strategy pattern
     *    - The interface PasswordGenerationStrategy defines the common
     *      operation for generating passwords.
     *    - BasicPasswordStrategy, EnhancedPasswordStrategy, and
     *      LettersPasswordStrategy are concrete strategies that each
     *      implement a different password-generation algorithm.
     *    - PasswordGeneratorService holds a reference to the current
     *      strategy and delegates generatePassword(int length) to it.
     *    - This design encapsulates the varying algorithms, makes the
     *      behavior swappable at run time via setAlgorithm(String name),
     *      and allows new algorithms to be added without changing client
     *      code that calls generatePassword.
     *
     * 2. Singleton pattern
     *    - PasswordGeneratorService has a private constructor, a private
     *      static 'instance' field, and a public static getInstance()
     *      method that lazily creates and returns the single instance.
     *    - This ensures that only one PasswordGeneratorService exists in
     *      the application and that there is a single shared access
     *      point, as required by the assignment.
     *
     * These patterns are appropriate because the problem requires:
     * - Multiple interchangeable algorithms that can vary independently
     *   of clients (Strategy).
     * - A single global service for password generation (Singleton).
     */
}
