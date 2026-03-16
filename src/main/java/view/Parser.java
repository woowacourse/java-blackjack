package view;

import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<String> parse(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .toList();
    }
}
