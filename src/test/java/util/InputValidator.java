package util;

public class InputValidator {
    public static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값은 비어 있을 수 없습니다.");
        }
    }
}
