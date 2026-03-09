package view;

public class InputValidator {
    public static void validateHitOption(String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("[ERROR] y 또는 n을 입력해주세요.");
        }
    }
}
