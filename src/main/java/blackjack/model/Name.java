package blackjack.model;

import blackjack.exception.ErrorCode;

public class Name {

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < 2 || name.length() > 5) {
            throw new IllegalArgumentException(ErrorCode.OUT_OF_NAME_LENGTH);
        }
    }

}
