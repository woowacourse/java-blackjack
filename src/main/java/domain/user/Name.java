package domain.user;

import domain.BlackjackException;

public record Name(String value) {
    private static final int MAX_NAME_SIZE = 5;

    public Name {
        validate(value);
    }

    private void validate(String value) {
        validateNameSize(value);
        validateBlankName(value);
    }

    private void validateBlankName(String value) {
        if (value.isBlank()) {
            throw new BlackjackException("공백인 이름이 존재합니다.");
        }
    }

    private void validateNameSize(String value) {
        if (value.length() > MAX_NAME_SIZE) {
            throw new BlackjackException("이름은 최대 5자입니다.");
        }
    }
}
