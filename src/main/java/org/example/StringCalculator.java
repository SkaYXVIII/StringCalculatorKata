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

        checkIfNotContainsNegatives(separatedNumbers);

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

    private void checkIfNotContainsNegatives(int[] numbers){
        int[] negativeNumbers = Arrays.stream(numbers).filter(v -> v<0).toArray();
        if(negativeNumbers.length > 0){
            StringBuilder negativesMsg = new StringBuilder();
            Arrays.stream(negativeNumbers).forEach(negativesMsg::append);
            throw new IllegalArgumentException("negatives not Allowed! cause: " + negativesMsg);
        }
    }
}
