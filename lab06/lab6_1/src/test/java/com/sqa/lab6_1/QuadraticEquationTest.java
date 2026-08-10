package com.sqa.lab6_1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticEquationTest {

    private final QuadraticEquation quadraticEquation = new QuadraticEquation();

    @ParameterizedTest(name = "Rule#{index} => a={0}, b={1}, c={2}, expected={3}")
    @CsvSource({
            // a,  b,  c,  expectedRootNature
    	"1,	4,	3,	REAL_ROOTS",
    	"1,	2,	1,	EQUAL_ROOTS",
    	"1, 1,	1,	IMAGINARY_ROOTS",
    	"0,	1,	1,	NOT_QUADRATIC"
    })
    void determineRootNature_limitedEntryDecisionTable(int a, int b, int c, RootNature expected) {
        RootNature actual = quadraticEquation.determineRootNature(a, b, c);
        assertEquals(expected, actual,
                () -> "a=" + a + ", b=" + b + ", c=" + c
                        + " expected " + expected + " but got " + actual);
    }
}