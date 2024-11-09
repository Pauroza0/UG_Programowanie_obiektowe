package com.github.pauroza0;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RPNCalculatorTest {
    RPNCalculator rpnCalculator = new RPNCalculator();

    @ParameterizedTest
    @CsvSource({
            "'5 3 +', 8",
            "'0 3 / ', 0",
            "'64 3 /', 21",
            "'4 3 %', 1",
            "'-4 3 %', 2",
            "'8 2 ^', 64",
            "'12 2 3 4 * 10 5 / + * +', 40",
            "'5 1 2 + 4 * + 3 -', 14",
            "'8 2 ^ 3 / 5 +', 26",
            "'20 7 % 7 / 2 + 5 ^', 32"
    })
    public void testShouldCalculateExpressionCorrectly(String expression, int expectedResult) {
        int result = rpnCalculator.calculateExpression(expression);

        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'5 0 /', 'Dzielenie przez 0'",
            "'4 5 6 7 8 + / /', 'Dzielenie przez 0'",
            "'4 0 %', 'Modulo 0'",
            "'4 2 + 2 * 20 5 % %', 'Modulo 0'"
    })
    public void testModuloOrDivisionByZeroThrowsException(String expression, String expectedMessage) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> rpnCalculator.calculateExpression(expression));

        assertEquals(expectedMessage, thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "d 3 +",
            "--22 3 +",
            "23 3 +d",
            "23d 3 +",
            "23 2 ++"
    })
    public void testInvalidCharacterInputThrowsException(String expression) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> rpnCalculator.calculateExpression(expression));

        assertEquals("Niedozwolony znak", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2",
            "2 +",
            "+ +",
            "2 2"
    })
    public void testExpressionTooShortOrTooLittleOperands(String expression) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> rpnCalculator.calculateExpression(expression));

        assertEquals("Za mało operandów na początku ciągu", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2 2 + 2",
            "4 5 5 6 +",
            "4 5 6 7 8 + / +"
    })
    public void testTooLittleOperationsToCalculate(String expression) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> rpnCalculator.calculateExpression(expression));

        assertEquals("Niepoprawny stosunek operatorów do operandów", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2 2 + +",
            "2 3 4 + - +",
            "2 3 + 4 * /"
    })
    public void testTooMuchOperations(String expression) {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> rpnCalculator.calculateExpression(expression));

        assertEquals("Za dużo operatorów, stos jest pusty", thrown.getMessage());
    }
}
