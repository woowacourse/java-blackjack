package utils;

import java.util.List;
import meesage.ErrorMessage;

public class InputParser {

    public static final String DELIMITER = ",";

    public static List<String> splitByDelimiter(String input) {
        validate(input);
        return List.of(input.split(DELIMITER));
    }

    private static void validate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }
}
