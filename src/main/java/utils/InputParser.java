package utils;

import java.util.List;

public class InputParser {

    public static final String DELIMITER = ",";

    public static List<String> splitByDelimiter(String input) {
        return List.of(input.split(DELIMITER));
    }
}
