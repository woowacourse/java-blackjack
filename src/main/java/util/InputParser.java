package util;

import java.util.*;
import java.util.stream.Collectors;

public class InputParser {
    public static List<String> splitComma(String input) {
        List<String> names = Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        validateDuplicate(names);

        return names;
    }

    private static void validateDuplicate(List<String> names) {
        Set<String> set = new HashSet<>(names);

        if (set.size() != names.size()) {
            throw new IllegalArgumentException();
        }
    }
}

