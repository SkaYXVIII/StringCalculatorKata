package org.example;

import java.util.Arrays;

public class StringCalculator {

    private String delimiters = ",\n";

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
        numbers = getOptionalDelimiterAndReturnNumbersWithoutOddLine(numbers);

        var separatedNumbers = numbers.split("[" + delimiters + "]");

        return Arrays.stream(separatedNumbers)
                .map(v -> v.replaceAll("\\s", ""))
                .filter(v -> !v.isBlank())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private String getOptionalDelimiterAndReturnNumbersWithoutOddLine(String numbers){
        if (numbers.startsWith("//")){
            int endIndexOfDelimiter = numbers.indexOf("\n");
            delimiters += numbers.substring(2, endIndexOfDelimiter);
            return numbers.substring(endIndexOfDelimiter + 1);
        }
        return numbers;
    }
}
