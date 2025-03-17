package view;

import java.util.List;

public class InputConverter {
    private static final String DELIMITER = ",";

    public static List<String> splitByDelimiter(final String input) {
        validateNotNullOrBlankInput(input);
        return List.of(input.trim().split(DELIMITER));
    }

    private static void validateNotNullOrBlankInput(final String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public static int parseToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
