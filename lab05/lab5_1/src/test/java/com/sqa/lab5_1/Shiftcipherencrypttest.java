package com.sqa.lab5_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.1 - Equivalence class testing
 *
 * Parameterized JUnit 5 tests for ShiftCipher.encrypt()
 * Test cases (TC001-TC013) are taken directly from sheet "1_Encrypt"
 * of Lab5_EC_xlsx_yoru2.xlsx (Weak Robust Equivalence Class Testing).
 */
class ShiftCipherEncryptTest {

    private final ShiftCipher cipher = new ShiftCipher();

    @ParameterizedTest(name = "{0}: encrypt(plainText=\"{1}\", key={2})")
    @MethodSource("encryptTestCases")
    void testEncrypt(String testCaseId, String plainText, int key,
                      String expected, String expectedExceptionSubstring) {
        if (expectedExceptionSubstring != null) {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> cipher.encrypt(plainText, key),
                    testCaseId + " should throw IllegalArgumentException"
            );
            assertTrue(
                    ex.getMessage().contains(expectedExceptionSubstring),
                    testCaseId + " - expected message to contain \"" + expectedExceptionSubstring
                            + "\" but was \"" + ex.getMessage() + "\""
            );
        } else {
            assertEquals(expected, cipher.encrypt(plainText, key), testCaseId + " failed");
        }
    }

    /**
     * testCaseId, plainText, key, expectedResult, expectedExceptionSubstring
     * expectedExceptionSubstring == null  -> normal (valid) case, compare expectedResult
     * expectedExceptionSubstring != null  -> exception case, expectedResult is ignored (null)
     */
    static Stream<Arguments> encryptTestCases() {
        return Stream.of(
                // Valid EC cases
                Arguments.of("TC001", "WELCOME", 0, "WELCOME", null),          // EC1 + EC3 (key = 0)
                Arguments.of("TC002", "welcome", 5, "BJQHTRJ", null),          // EC2 (lowercase) + EC4 (key 1-25)
                Arguments.of("TC003", "WELCOME", -3, "TBIZLJB", null),         // EC1 + EC5 (negative key)
                Arguments.of("TC004", "welcome", 30, "AIPGSQI", null),         // EC2 + EC6 (key >= 26)

                // Invalid EC cases - plainText
                Arguments.of("TC005", null, 3, null, "Empty string is not accepted"),   // EC1 invalid: null
                Arguments.of("TC006", "", 3, null, "Empty string is not accepted"),      // EC2 invalid: empty string
                Arguments.of("TC007", "HELLO123", 3, null, "'1'"),             // EC3 invalid: contains digits
                Arguments.of("TC008", "HELLO!", 3, null, "'!'"),               // EC4 invalid: special character
                Arguments.of("TC009", "HELLO WORLD", 3, null, "' '"),          // EC5 invalid: contains space

                // Boundary cases
                Arguments.of("TC010", "A", 1, "B", null),
                Arguments.of("TC011", "ATTACK", 26, "ATTACK", null),
                Arguments.of("TC012", "ATTACK", -26, "ATTACK", null),
                Arguments.of("TC013", "ATTACK", 17, "RKKRTB", null)
        );
    }
}