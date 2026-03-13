package util;

public final class InputParser {
    private static final String DELIMITER = ",";
    private static final String VALID_INPUT_PATTERN = "^[a-zA-Z]+(,[a-zA-Z]+)*$";

    private InputParser() {
    }

    public static String[] parseName(String input) {
        validate(input);
        return input.split(DELIMITER);
    }

    private static void validate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어 있습니다.");
        }
        if (!input.matches(VALID_INPUT_PATTERN)) {
            throw new IllegalArgumentException("[ERROR] 입력 값은 영문자와 쉼표(,)로만 구성되어야 하며, 쉼표는 이름 사이에만 위치해야 합니다.");
        }
    }
}
