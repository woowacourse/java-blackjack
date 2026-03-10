package view.util;

public class InputValidator {
    private static final String INVALID_INPUT_ERROR_MESSAGE = "[ERROR] 올바르지 않은 입력입니다!";
    private static final String NOT_Y_AND_N_ERROR_MESSAGE = "[ERROR] y나 n이 아닙니다!";
    private static final String HIT_COMMAND = "y";
    private static final String STAND_COMMAND = "n";

    public static void validateInput(String input) {
        if (input != null && input.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        }
    }

    public static void validateChoiceInput(String input) {
        validateInput(input);
        isNotHitAndStand(input);
    }

    private static void isNotHitAndStand(String input) {
        if (!input.equals(HIT_COMMAND) && !input.equals(STAND_COMMAND)) {
            throw new IllegalArgumentException(NOT_Y_AND_N_ERROR_MESSAGE);
        }
    }

}
