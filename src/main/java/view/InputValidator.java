package view;

public class InputValidator {

    private static final String BLANK_EXCEPTION_MESSAGE = "[ERROR] 하나 이상의 문자를 입력해야 합니다.";

    public void validateNotBlank(final String target) {
        if (target.isBlank()) {
            throw new IllegalArgumentException(BLANK_EXCEPTION_MESSAGE);
        }
    }
}
