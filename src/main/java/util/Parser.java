package util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    private static final String delimiter = ",";

    public static List<String> parseByDelimiter(String delimiter, String input) {
        return Arrays.stream(input.split(delimiter))
                .map(String::strip)
                .toList();
    }
}
