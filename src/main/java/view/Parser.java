package view;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private Parser() {
    }

    public static List<String> parseStringToList(String names) {
        return Arrays.stream(names.split(","))
                .toList()
                .stream()
                .map(String::trim)
                .toList();
    }

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("");
        }
    }
}
