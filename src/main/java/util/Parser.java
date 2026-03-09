package util;


import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final String DELIMITER = ",";

    public static List<String> parse(String inputs) {
        return Arrays.stream(inputs.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
