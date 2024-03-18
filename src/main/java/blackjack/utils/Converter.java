package blackjack.utils;

import java.util.Arrays;
import java.util.List;

public class Converter {
    private static final String DELIMITER = ",";

    private Converter() {
    }

    public static List<String> stringToList(final String input) {
        return Arrays.stream(input.split(DELIMITER, -1))
                .map(String::strip)
                .toList();
    }
}
