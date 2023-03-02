package domain;

import java.util.ArrayList;

import view.ErrorMessage;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        super(new ArrayList<>(), name);
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNotNull(name);
        validateNotEmpty(name);
        validateNoDealer(name);
        validateDoesNotContainComma(name);
        validateNameLength(name);
    }



    private void validateNotEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_EMPTY.getMessage());
        }
    }

    private void validateNotNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_NULL.getMessage());
        }
    }

    private void validateNoDealer(String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DEALER.getMessage());
        }
    }

    private void validateDoesNotContainComma(String name) {
        if (name.contains(",")) {
            throw new IllegalArgumentException(ErrorMessage.NAME_CONTAINS_COMMA.getMessage());
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH.getMessage());
        }
    }
}
