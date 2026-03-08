package util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static List<String> parseName(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::strip)
                .toList();
    }
}
