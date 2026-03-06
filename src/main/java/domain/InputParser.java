package domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class InputParser {
    public static List<String> parsePlayerNames(String inputNames) {
        try {
            List<String> names = Arrays.stream(inputNames.split(","))
                            .map(String::trim)
                            .toList();
            InputValidator.validateInputNames(names);
            return names;
        } catch (PatternSyntaxException exception) {
            throw new IllegalArgumentException();
        }
    }
}
