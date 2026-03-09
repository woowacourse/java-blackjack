package view;

public class InputValidator {
    public static void validateInputEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }

    public static void validateHitOption(String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("[ERROR] y 또는 n을 입력해주세요.");
        }
    }
}
