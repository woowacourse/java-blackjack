package view;

import java.util.List;

public class InputParser {
    private static final String DELIMITER = ",";

    public static List<String> splitByDelimiter(final String input) {
        return List.of(input.trim().split(DELIMITER));
    }
}
