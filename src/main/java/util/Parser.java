package util;

import exception.ExceptionMessage;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final String DELIMITER = ",";

    private Parser() {
    }

    public static List<String> parseByDelimiter(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .toList();
    }

    public static long parseByBattingAmount(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BATTING_INPUT.getMessage());
        }
    }
}
