package util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<String> splitToDelimiter(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .toList();
    }

    public static int toInt(String input) {
        InputValidator.validateBetAmount(input);

        return Integer.parseInt(input.trim());
    }
}
