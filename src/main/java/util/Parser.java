package util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final int SPLIT_KEEP_EMPTY_TOKENS = -1;

    public static List<String> splitByDelimiter(String rawValue, String delimiter) {
        String[] splittedValues = rawValue.split(delimiter, SPLIT_KEEP_EMPTY_TOKENS);

        return Arrays.stream(splittedValues)
                .map(String::trim)
                .toList();
    }
}
