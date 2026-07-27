package com.sqa.lab5_2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CP353201 Software Quality Assurance (1/2569)
 * Lab#5.2 - Equivalence class testing
 *
 * Parameterized JUnit 5 tests for CompetitionScore.findMaxScore(...)
 * Test cases are taken directly from sheet "2_CompetitionScore"
 * of Lab5_EC_xlsx_yoru2.xlsx (Strong Robust Equivalence Class Testing).
 *
 * TC001-TC025 apply to BOTH overloads: findMaxScore(int,int,int) and findMaxScore(int[]).
 * TC026-TC037 are array-only cases (invalid array length), so they only apply
 * to findMaxScore(int[]).
 */
class CompetitionScoreTest {

    private final CompetitionScore competitionScore = new CompetitionScore();

    // ---------------------------------------------------------------
    // findMaxScore(int score1, int score2, int score3)
    // ---------------------------------------------------------------

    @ParameterizedTest(name = "{0}: findMaxScore({1}, {2}, {3})")
    @MethodSource("threeArgTestCases")
    void testFindMaxScoreThreeArgs(String testCaseId, int score1, int score2, int score3,
                                    Integer expected, String expectedExceptionSubstring) {
        if (expectedExceptionSubstring != null) {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> competitionScore.findMaxScore(score1, score2, score3),
                    testCaseId + " should throw IllegalArgumentException"
            );
            assertTrue(
                    ex.getMessage().contains(expectedExceptionSubstring),
                    testCaseId + " - expected message to contain \"" + expectedExceptionSubstring
                            + "\" but was \"" + ex.getMessage() + "\""
            );
        } else {
            assertEquals(expected, competitionScore.findMaxScore(score1, score2, score3), testCaseId + " failed");
        }
    }

    /**
     * testCaseId, score1, score2, score3, expectedResult, expectedExceptionSubstring
     */
    static Stream<Arguments> threeArgTestCases() {
        return Stream.of(
                // Valid EC cases
                Arguments.of("TC001", 480, 100, 200, 480, null),   // EC1: score1 is max
                Arguments.of("TC002", 100, 450, 200, 450, null),   // EC2: score2 is max
                Arguments.of("TC003", 100, 200, 470, 470, null),   // EC3: score3 is max
                Arguments.of("TC004", 300, 300, 300, 300, null),   // EC4: all equal
                Arguments.of("TC005", 0, 0, 0, 0, null),           // EC5: result at lower boundary = 0
                Arguments.of("TC006", 500, 250, 100, 500, null),   // EC6: result at upper boundary = 500

                // Single invalid score
                Arguments.of("TC007", -1, 200, 100, null, "input score is -1."),
                Arguments.of("TC008", 501, 200, 100, null, "input score is 501."),
                Arguments.of("TC009", 100, -5, 200, null, "input score is -5."),
                Arguments.of("TC010", 100, 600, 200, null, "input score is 600."),
                Arguments.of("TC011", 100, 200, -10, null, "input score is -10."),
                Arguments.of("TC012", 100, 200, 800, null, "input score is 800."),

                // Two invalid scores (score1 & score2)
                Arguments.of("TC013", -1, -5, 100, null, "input score is -1."),
                Arguments.of("TC014", -1, 600, 100, null, "input score is -1."),
                Arguments.of("TC015", 501, -5, 100, null, "input score is 501."),
                Arguments.of("TC016", 501, 600, 100, null, "input score is 501."),

                // Two invalid scores (score1 & score3)
                Arguments.of("TC017", -1, 100, -10, null, "input score is -1."),
                Arguments.of("TC018", -1, 100, 600, null, "input score is -1."),
                Arguments.of("TC019", 501, 100, -10, null, "input score is 501."),
                Arguments.of("TC020", 501, 100, 600, null, "input score is 501."),

                // Two invalid scores (score2 & score3)
                Arguments.of("TC021", 100, -5, -10, null, "input score is -5."),
                Arguments.of("TC022", 100, -5, 600, null, "input score is -5."),
                Arguments.of("TC023", 100, 600, -10, null, "input score is 600."),
                Arguments.of("TC024", 100, 600, 600, null, "input score is 600."),

                // Triple fault - all three invalid, score1 validated first
                Arguments.of("TC025", -1, 600, -50, null, "input score is -1.")
        );
    }

    // ---------------------------------------------------------------
    // findMaxScore(int[] scores)
    // ---------------------------------------------------------------

    @ParameterizedTest(name = "{0}: findMaxScore({1})")
    @MethodSource("arrayTestCases")
    void testFindMaxScoreArray(String testCaseId, int[] scores,
                                Integer expected, String expectedExceptionSubstring) {
        if (expectedExceptionSubstring != null) {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> competitionScore.findMaxScore(scores),
                    testCaseId + " should throw IllegalArgumentException"
            );
            assertTrue(
                    ex.getMessage().contains(expectedExceptionSubstring),
                    testCaseId + " - expected message to contain \"" + expectedExceptionSubstring
                            + "\" but was \"" + ex.getMessage() + "\""
            );
        } else {
            assertEquals(expected, competitionScore.findMaxScore(scores), testCaseId + " failed");
        }
    }

    /**
     * testCaseId, scores array, expectedResult, expectedExceptionSubstring
     */
    static Stream<Arguments> arrayTestCases() {
        return Stream.of(
                // Same 25 cases as the three-arg overload, using array form
                Arguments.of("TC001", new int[]{480, 100, 200}, 480, null),
                Arguments.of("TC002", new int[]{100, 450, 200}, 450, null),
                Arguments.of("TC003", new int[]{100, 200, 470}, 470, null),
                Arguments.of("TC004", new int[]{300, 300, 300}, 300, null),
                Arguments.of("TC005", new int[]{0, 0, 0}, 0, null),
                Arguments.of("TC006", new int[]{500, 250, 100}, 500, null),

                Arguments.of("TC007", new int[]{-1, 200, 100}, null, "input score is -1."),
                Arguments.of("TC008", new int[]{501, 200, 100}, null, "input score is 501."),
                Arguments.of("TC009", new int[]{100, -5, 200}, null, "input score is -5."),
                Arguments.of("TC010", new int[]{100, 600, 200}, null, "input score is 600."),
                Arguments.of("TC011", new int[]{100, 200, -10}, null, "input score is -10."),
                Arguments.of("TC012", new int[]{100, 200, 800}, null, "input score is 800."),

                Arguments.of("TC013", new int[]{-1, -5, 100}, null, "input score is -1."),
                Arguments.of("TC014", new int[]{-1, 600, 100}, null, "input score is -1."),
                Arguments.of("TC015", new int[]{501, -5, 100}, null, "input score is 501."),
                Arguments.of("TC016", new int[]{501, 600, 100}, null, "input score is 501."),

                Arguments.of("TC017", new int[]{-1, 100, -10}, null, "input score is -1."),
                Arguments.of("TC018", new int[]{-1, 100, 600}, null, "input score is -1."),
                Arguments.of("TC019", new int[]{501, 100, -10}, null, "input score is 501."),
                Arguments.of("TC020", new int[]{501, 100, 600}, null, "input score is 501."),

                Arguments.of("TC021", new int[]{100, -5, -10}, null, "input score is -5."),
                Arguments.of("TC022", new int[]{100, -5, 600}, null, "input score is -5."),
                Arguments.of("TC023", new int[]{100, 600, -10}, null, "input score is 600."),
                Arguments.of("TC024", new int[]{100, 600, 600}, null, "input score is 600."),

                Arguments.of("TC025", new int[]{-1, 600, -50}, null, "input score is -1."),

                // Array-only cases: invalid array length (EC7: length > 3, EC8: length < 3)
                // Length is checked before any score validation, so these throw a length error
                // even when some values would also be out of range.
                Arguments.of("TC026", new int[]{100, 200, 300, 150}, null, "found 4 attempts"),
                Arguments.of("TC027", new int[]{100, 200}, null, "found 2 attempts"),
                Arguments.of("TC028", new int[]{-1, 200, 100, 50}, null, "found 4 attempts"),
                Arguments.of("TC029", new int[]{600, 200, 100, 50}, null, "found 4 attempts"),
                Arguments.of("TC030", new int[]{100, -5, 200, 50}, null, "found 4 attempts"),
                Arguments.of("TC031", new int[]{100, 600, 200, 50}, null, "found 4 attempts"),
                Arguments.of("TC032", new int[]{100, 200, -10, 50}, null, "found 4 attempts"),
                Arguments.of("TC033", new int[]{100, 200, 800, 50}, null, "found 4 attempts"),
                Arguments.of("TC034", new int[]{-1, 200}, null, "found 2 attempts"),
                Arguments.of("TC035", new int[]{600, 200}, null, "found 2 attempts"),
                Arguments.of("TC036", new int[]{100, -5}, null, "found 2 attempts"),
                Arguments.of("TC037", new int[]{100, 600}, null, "found 2 attempts")
        );
    }

    // ---------------------------------------------------------------
    // Extra case covered by the code (null array) but not listed as a
    // separate TC row in the Excel sheet - included for completeness.
    // ---------------------------------------------------------------
    @Test
    void testFindMaxScoreArray_nullArray_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> competitionScore.findMaxScore((int[]) null)
        );
        assertTrue(ex.getMessage().contains("A score cannot be null!!"));
    }
}