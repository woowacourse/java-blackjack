package blackjack.domain;

import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 100;
    private static final String EMPTY_OR_NULL_EXCEPTION_MESSAGE = "Empty or null names exception.";
    private static final String OVER_MAX_NAME_LENGTH_EXCEPTION_MESSAGE = "Too long name exception.";

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name.trim();
    }

    private void validateName(String name) {
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_EXCEPTION_MESSAGE);
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(OVER_MAX_NAME_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
