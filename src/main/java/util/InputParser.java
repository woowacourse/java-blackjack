package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputParser {
    public static final String ERROR_NAMES_EMPTY = "[ERROR] 이름은 비어있을 수 없습니다.";
    private static final String YES = "y";
    private static final String NO = "n";

    private static void validateEmptyInput(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NAMES_EMPTY);
        }
    }

    private static void validateInputNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> parseName(String input) {
        validateInputNull(input);

        StringTokenizer stringTokenizer = new StringTokenizer(input.strip(), ",");
        List<String> names = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {
            names.add(stringTokenizer.nextToken().strip());
        }

        validateEmptyInput(names);

        return names;
    }

    public String parseUserChoice(String choice) {
        validateInputNull(choice);
        String normalizedChoice = choice.toLowerCase().strip();

        if (normalizedChoice.equals(NO)) {
            return NO;
        }

        if (normalizedChoice.equals(YES)) {
            return YES;
        }

        throw new IllegalArgumentException();
    }
}
