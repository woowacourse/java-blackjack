package view.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParse {
    public static List<String> separateBySeparator(String input) {

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
