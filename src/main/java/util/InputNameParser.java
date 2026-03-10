package util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class InputNameParser {

    private InputNameParser() {
    }

    public static List<String> parsePlayerNames(String inputNames) {
        try {
            List<String> names = Arrays.stream(inputNames.split(",", -1))
                    .map(String::strip)
                    .toList();
            InputNameValidator.validateInputNames(names);
            return names;
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
