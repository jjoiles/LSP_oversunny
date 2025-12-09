package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for {@link PasswordGeneratorService}.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
        // Ensure each test starts with no algorithm selected
        service.clearAlgorithmForTesting();
    }

    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "getInstance() should never return null.");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();

        // Must be the exact same object in memory (true Singleton)
        assertSame(service, second,
                "getInstance() must always return the same singleton instance.");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        s.clearAlgorithmForTesting(); // just to be explicit

        assertThrows(IllegalStateException.class,
                () -> s.generatePassword(8),
                "generatePassword should throw IllegalStateException when " +
                        "no algorithm has been selected.");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);

        assertEquals(10, p.length(), "Basic algorithm must generate 10 characters.");

        for (char c : p.toCharArray()) {
            assertTrue(Character.isDigit(c),
                    "Basic algorithm must generate digits only, but found: " + c);
        }
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);

        assertEquals(12, p.length(),
                "Enhanced algorithm must generate 12 characters.");

        String allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                       + "abcdefghijklmnopqrstuvwxyz"
                       + "0123456789";

        for (char c : p.toCharArray()) {
            assertTrue(allowed.indexOf(c) >= 0,
                    "Enhanced algorithm generated invalid character: " + c);
        }
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);

        assertEquals(8, p.length(),
                "Letters algorithm must generate 8 characters.");

        for (char c : p.toCharArray()) {
            assertTrue(Character.isLetter(c),
                    "Letters algorithm must generate letters only, but found: " + c);
        }
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // Length checks
        assertEquals(10, p1.length());
        assertEquals(10, p2.length());
        assertEquals(10, p3.length());

        // Character-set checks
        for (char c : p1.toCharArray()) {
            assertTrue(Character.isDigit(c),
                    "Basic password must contain digits only.");
        }

        for (char c : p2.toCharArray()) {
            assertTrue(Character.isLetter(c),
                    "Letters password must contain letters only.");
        }

        String allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                       + "abcdefghijklmnopqrstuvwxyz"
                       + "0123456789";
        for (char c : p3.toCharArray()) {
            assertTrue(allowed.indexOf(c) >= 0,
                    "Enhanced password must contain only allowed characters.");
        }

        // Basic vs letters must differ because they use disjoint character sets
        assertNotEquals(p1, p2,
                "Digit-only and letters-only passwords should not be identical.");
    }
}

