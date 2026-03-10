package blackjack.util;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {
    }

    public static List<String> parseByDelimiter(String delimiter, String input) {
        return Arrays.stream(input.split(delimiter))
                .map(String::strip)
                .toList();
    }
}
