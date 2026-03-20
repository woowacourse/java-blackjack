package utils;

import java.util.List;
import meesage.ErrorMessage;

public class InputParser {

    private static final String DELIMITER = ",";
    private static final String STRICT_NUMERIC_PATTERN = "[1-9]\\d*";


    public static List<String> splitByDelimiter(String input) {
        validateEmpty(input);
        return List.of(input.split(DELIMITER));
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }

    public static int parseIntStrict(String input) {
        validateEmpty(input);
        validateNumericPattern(input);
        return Integer.parseInt(input);
    }

    private static void validateNumericPattern(String input) {
        if (!input.matches(STRICT_NUMERIC_PATTERN)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_STRICT_NUMERIC.getMessage());
        }
    }
}
