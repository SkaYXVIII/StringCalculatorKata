package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    StringCalculator stringCalculator = new StringCalculator();

    @Test
    void addTwoCorrectNumbers() {
        assertEquals(3, stringCalculator.Add("1,2"));
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
        assertEquals(6, stringCalculator.Add("1,2,3"));
        assertEquals(55, stringCalculator.Add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    void addNotANumber() {
        assertThrows(NumberFormatException.class, () -> {
            stringCalculator.Add("1, two");
        });
    }

    @Test
    void addNumbersWithMultipleDelimiters(){
        assertEquals(6, stringCalculator.Add("1\n2,3"));
        assertEquals(1, stringCalculator.Add("1,\n"));
    }

    @Test
    void addNumbersWithOptionalDelimiter(){
        assertEquals(3, stringCalculator.Add("//;\n1;2"));
        assertEquals(3, stringCalculator.Add("//;+\n1;+2"));
        assertEquals(6, stringCalculator.Add("//;+\n1;2+3"));
        assertEquals(6, stringCalculator.Add("//\n1,2\n3"));
    }

    @Test
    void addWithNegatives(){
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    stringCalculator.Add("1,-2,-3,4,-6");
                });
        assertEquals("negatives not Allowed! cause: -2-3-6",thrown.getMessage());
    }
}