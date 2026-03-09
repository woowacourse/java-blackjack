package util;

public class InputParser {
    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static String[] parseName(String input) {
        return input.split(DELIMITER);
    }
}
