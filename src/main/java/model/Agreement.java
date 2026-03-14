package model;

import exception.GameException;

public class Agreement {
    private static final String TRUE_TEXT = "y";
    private static final String FALSE_TEXT = "n";

    private final boolean value;

    public Agreement(String value) {
        validate(value);
        this.value = value.equals(TRUE_TEXT);
    }

    public boolean get() {
        return value;
    }

    private void validate(String value) {
        validateEmptyValue(value);
        validatePossibleValue(value);
    }

    private void validateEmptyValue(String value) {
        if (value.isBlank()) {
            throw new GameException("빈 값을 입력하셨습니다.");
        }
    }

    private void validatePossibleValue(String value) {
        if (!value.equals(TRUE_TEXT) && !value.equals(FALSE_TEXT)) {
            throw new GameException("유효한 형식의 입력으로 넣어주세요(y 또는 n)");
        }
    }
}
