package domain.participant;

import domain.ExceptionCode;

import java.util.ArrayList;

public class Player extends Participant {
    private static final int MAX_PLAYER_NAME_LENGTH = 10;
    private static final String INVALID_NAME_CHARACTER = ",";
    private Player(String name) {
        super(new ArrayList<>(), name);
    }

    public static Player create(String name) {
        validate(name);
        return new Player(name);
    }

    private static void validate(String name) {
        validateNotNull(name);
        validateNotEmpty(name);
        validateNoDealer(name);
        validateDoesNotContainComma(name);
        validateNameLength(name);
    }

    private static void validateNotNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ExceptionCode.NAME_IS_NULL.getExceptionCode());
        }
    }

    private static void validateNotEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ExceptionCode.NAME_IS_EMPTY.getExceptionCode());
        }
    }

    private static void validateNoDealer(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ExceptionCode.PLAYER_INVALID_NAME.getExceptionCode());
        }
    }

    private static void validateDoesNotContainComma(String name) {
        if (name.contains(INVALID_NAME_CHARACTER)) {
            throw new IllegalArgumentException(ExceptionCode.NOT_CONTAINS_COMMA_PLAYER_NAME.getExceptionCode());
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() > MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionCode.OUT_OF_RANGE_PLAYER_NAME_LENGTH.getExceptionCode());
        }
    }

    @Override
    public boolean shouldHit() {
        throw new UnsupportedOperationException();
    }
}
