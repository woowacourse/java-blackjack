package util;

public class Validator {
    private static final String INVALID_INPUT_ERROR_MESSAGE = "[ERROR] 올바르지 않은 입력입니다!";
    private static final String NOT_Y_AND_N_ERROR_MESSAGE = "[ERROR] y나 n이 아닙니다!";

    public static void validateInput(String input) {
        if (input != null && input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }

    public static void validateChoiceInput(String input) {
        validateInput(input);
        isNotYAndN(input);
    }

    private static void isNotYAndN(String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException(NOT_Y_AND_N_ERROR_MESSAGE);
        }
    }

}
