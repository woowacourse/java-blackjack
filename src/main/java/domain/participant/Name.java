package domain.participant;

import domain.ExceptionCode;

public class Name {

    private static final int MAX_PLAYER_NAME_LENGTH = 10;
    private static final String INVALID_NAME_CHARACTER = ",";
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    void validate(String name) {
        validateNotNull(name);
        validateNotEmpty(name);
        validateNoDealer(name);
        validateDoesNotContainComma(name);
        validateNameLength(name);
    }

    private void validateNotNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ExceptionCode.NAME_IS_NULL.getExceptionCode());
        }
    }

    private void validateNotEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ExceptionCode.NAME_IS_EMPTY.getExceptionCode());
        }
    }

    private void validateNoDealer(String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException(ExceptionCode.PLAYER_INVALID_NAME.getExceptionCode());
        }
    }

    private void validateDoesNotContainComma(String name) {
        if (name.contains(INVALID_NAME_CHARACTER)) {
            throw new IllegalArgumentException(ExceptionCode.NOT_CONTAINS_COMMA_PLAYER_NAME.getExceptionCode());
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionCode.OUT_OF_RANGE_PLAYER_NAME_LENGTH.getExceptionCode());
        }
    }

    public String getName() {
        return name;
    }
}
