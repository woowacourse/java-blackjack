package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<String> parseInput(String input, String delimiter) {
        List<String> parsedInput = new ArrayList<>(Arrays.asList(input.split(delimiter, -1)));
        return parsedInput.stream()
            .map(String::strip)
            .toList();
    }
}

