package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputParser {
    private static final String YES = "y";
    private static final String NO = "n";

    public List<String> parseName(String input) {
        StringTokenizer stringTokenizer = new StringTokenizer(input.strip(), ",");
        List<String> names = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {
            names.add(stringTokenizer.nextToken().strip());
        }

        validateEmptyInput(names);

        return names;
    }

    private static void validateEmptyInput(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public String parseUserChoice(String choice) {
        String normalizedChoice  = choice.toLowerCase().strip();

        if (normalizedChoice.equals(NO)) {
            return NO;
        }

        if (normalizedChoice.equals(YES)) {
            return YES;
        }

        throw new IllegalArgumentException();
    }
}
