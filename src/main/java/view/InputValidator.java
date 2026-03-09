package view;

public class InputValidator {
    public static void validateInputEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }
}
