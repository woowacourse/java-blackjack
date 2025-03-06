package util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> splitByDelimiter(String rawValue, String delimiter) {
        String[] splittedValues = rawValue.split(delimiter);

        return Arrays.stream(splittedValues)
                .map(String::trim)
                .toList();
    }
}
