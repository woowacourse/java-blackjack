package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String SEPARATOR = ",";

    public static List<String> separateBySeparator(String input) {
        return Arrays.stream(input.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
