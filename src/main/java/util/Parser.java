package util;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String DELIMITER = ",";

    public static List<String> parseInput(String input) {
        List<String> parsedInput = new ArrayList<>(List.of(input.split(DELIMITER, -1)));
        return parsedInput.stream()
            .map(String::strip)
            .toList();
    }
}
