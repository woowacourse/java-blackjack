package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public static List<String> parseByDelimiter(final String value, final String delimiter) {
        return Arrays.asList(value.split(delimiter, -1));
    }

    public static List<String> trim(final List<String> values) {
        return values.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
