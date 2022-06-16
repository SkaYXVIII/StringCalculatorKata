package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    void addTwoCorrectNumbers() {
        assertEquals(3, stringCalculator.Add("1,2"));
        assertEquals(-1, stringCalculator.Add("1,-2"));
    }

    @Test
    void addOneArgument() {
        assertEquals(5, stringCalculator.Add("5"));
    }

    @Test
    void addBlankOrEmptyString() {
        assertEquals(0, stringCalculator.Add(""));
        assertEquals(0, stringCalculator.Add("  "));
        assertEquals(0, stringCalculator.Add(", "));
    }

    @Test
    void addMoreThanTwoNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.Add("1,2,3");
        });
    }

    @Test
    void addNotANumber() {
        assertThrows(NumberFormatException.class, () -> {
            stringCalculator.Add("1, two");
        });
    }
}