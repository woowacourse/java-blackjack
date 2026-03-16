package util;

import domain.participant.Name;

import java.util.Arrays;
import java.util.List;

public class InputNameParser {

    private static final String DELIMITER = ",";

    private InputNameParser() {
    }

    public static List<Name> parsePlayerNames(String inputNames) {
        try {
            return Arrays.stream(inputNames.split(DELIMITER, -1))
                    .map(Name::new)
                    .toList();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
