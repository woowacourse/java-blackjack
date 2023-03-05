package view;

public class InputValidator {

    public static final String INPUT_EMPTY_ERROR_MESSAGE = "[ERROR] 입력값이 존재하지 않습니다.";

    public static void validateBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(INPUT_EMPTY_ERROR_MESSAGE);
        }
    }
}
