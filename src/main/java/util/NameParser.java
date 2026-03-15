package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NameParser {
    public static List<String> makeNameList(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
