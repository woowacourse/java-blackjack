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
}
