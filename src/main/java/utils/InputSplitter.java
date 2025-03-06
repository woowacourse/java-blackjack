package utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputSplitter {

    private InputSplitter() {
    }

    private static final String DELIMITER = ",";

    public static List<String> split(String value) {
        return Stream.of(value.split(DELIMITER))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
