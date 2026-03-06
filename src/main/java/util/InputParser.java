package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputParser {


    public List<String> parseName(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input.strip(), ",");
        List<String> names = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {
            names.add(stringTokenizer.nextToken());
        }

        validateEmptyInput(names);

        return names;
    }

    private static void validateEmptyInput(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
