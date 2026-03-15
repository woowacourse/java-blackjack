package utils;

import exception.BlankInputException;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> splitBy(String input, String delimiter) {
        List<String> result = Arrays.stream(input.split(delimiter, -1))
                .map(String::strip)
                .toList();

        if (result.stream().anyMatch(String::isBlank)) {
            throw new BlankInputException();
        }

        return result;
    }
}