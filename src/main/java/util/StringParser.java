package util;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    private static final String NAME_DELIMITER = ",";

    public static List<String> parse(String input) {
        return Arrays.stream(input.split(NAME_DELIMITER))
                .toList();
    }
}
