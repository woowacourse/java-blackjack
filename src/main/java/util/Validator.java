package util;

public class Validator {
    private static final String INVALID_INPUT_ERROR_MESSAGE = "[ERROR] 올바르지 않은 입력입니다!";
    private static final String NOT_Y_AND_N_ERROR_MESSAGE = "[ERROR] y나 n이 아닙니다!";
    private static final String HIT_COMMAND = "y";
    private static final String STAND_COMMAND = "n";

    public static void validateInputName(String input) {
        validateInputIsNotNullOrBlank(input);
    }

    public static void validateChoiceInput(String input) {
        validateInputIsNotNullOrBlank(input);
        isNotYAndN(input);
    }

    private static void validateInputIsNotNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }

    private static void isNotYAndN(String input) {
        if (!input.equals(HIT_COMMAND) && !input.equals(STAND_COMMAND)) {
            throw new IllegalArgumentException(NOT_Y_AND_N_ERROR_MESSAGE);
        }
    }
}
