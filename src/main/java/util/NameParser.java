package util;

import java.util.Arrays;
import java.util.List;

public class NameParser {
    public static List<String> parse(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank())
                .toList();
    }
}
