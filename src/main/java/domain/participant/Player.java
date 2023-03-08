package domain.participant;

import view.ErrorMessage;

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
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_NULL.getMessage());
        }
    }

    private static void validateNotEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_EMPTY.getMessage());
        }
    }

    private static void validateNoDealer(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DEALER.getMessage());
        }
    }

    private static void validateDoesNotContainComma(String name) {
        if (name.contains(INVALID_NAME_CHARACTER)) {
            throw new IllegalArgumentException(ErrorMessage.NAME_CONTAINS_COMMA.getMessage());
        }
    }

    private static void validateNameLength(String name) {
        if (name.length() > MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH.getMessage());
        }
    }
}
