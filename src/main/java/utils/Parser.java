package utils;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> splitBy(String input, String symbols) {
        List<String> result = Arrays.stream(input.split(symbols, -1))
                .map(String::trim)
                .toList();

        if (result.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("[ERROR] 빈 값이 포함되어 있습니다.");
        }

        return result;
    }
}