package org.example;

import java.util.Arrays;

public class StringCalculator {
    public int Add(String numbers) {

        if (numbers.isBlank())
            return 0;
        int[] separatedNumbers;

        try {
            separatedNumbers = convertToIntegers(numbers);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Only Integers type accepted! example: (\"1,2\")");
        }

        return Arrays.stream(separatedNumbers)
                .sum();
    }

    private int[] convertToIntegers(String numbers) throws NumberFormatException {
        var separatedNumbers = numbers.split(",");

        if (separatedNumbers.length > 2)
            throw new IllegalArgumentException("Only 2 numbers permitted");
        return Arrays.stream(separatedNumbers)
                .map(v -> v.replaceAll("\\s", ""))
                .filter(v -> !v.isBlank())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
