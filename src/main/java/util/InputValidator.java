package util;

public class InputValidator {
    public static void validatePlayerNameInput(String input) {
        validateBlank(input);
        validateDelimiter(input);
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값은 비어 있을 수 없습니다.");
        }
    }

    private static void validateDelimiter(String input) {
        if (!input.matches("^[가-힣a-z, ]+$")) {
            throw new IllegalArgumentException("[ERROR] 쉼표(,)를 제외한 특수문자는 사용할 수 없습니다.");
        }
    }
}
