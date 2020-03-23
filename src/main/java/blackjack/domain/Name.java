package blackjack.domain;

import blackjack.util.NullChecker;
import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 10;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
