package util;

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
}
