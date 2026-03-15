package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String SEPARATOR = ",";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "숫자만 입력해주세요.";

    public static List<String> separateBySeparator(String input) {
        return Arrays.stream(input.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int inputToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }
}
