package util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<String> parsePlayerNames(String inputNames) {
        return Arrays.stream(inputNames.split(",", -1))
                .map(String::strip)
                .toList();
    }
}
