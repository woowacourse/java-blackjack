package utils;

import java.util.Arrays;
import java.util.List;

import meesage.ErrorMessage;

public class InputParser {

    private static final String DELIMITER = ",";

    public static List<String> splitByDelimiter(String input) {
        validate(input);

        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .toList();
        validateNames(names);

        return names;
    }

    private static void validate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }

    private static void validateNames(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDE_NAMES_SIZE.getMessage());
        }
    }
}
