package blackjack.domain;

import blackjack.util.NullChecker;

public class Name {

    private static final int MAX_NAME_LENGTH = 100;
    private static final String EMPTY_EXCEPTION_MESSAGE = "Empty names exception.";
    private static final String OVER_MAX_NAME_LENGTH_EXCEPTION_MESSAGE = "Too long name exception.";

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name.trim();
    }

    private void validateName(String name) {
        NullChecker.validateNotNull(name);
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_EXCEPTION_MESSAGE);
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
