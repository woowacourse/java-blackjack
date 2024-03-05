package blackjack.domain;

import blackjack.exception.InvalidNameLengthException;
import blackjack.exception.NonAlphabeticNameException;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String NAME_FORMAT = "^[a-zA-Z]*$";

    private final String value;

    public Name(String value) {
        validateNameLength(value);
        validateIsAlphabetic(value);
        this.value = value;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidNameLengthException();
        }
    }

    private void validateIsAlphabetic(String name) {
        if (!name.matches(NAME_FORMAT)) {
            throw new NonAlphabeticNameException();
        }
    }
}
