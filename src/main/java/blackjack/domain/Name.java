package blackjack.domain;

import blackjack.domain.exception.InvalidPlayerNameException;
import java.util.Objects;

public class Name {
    private final String value;

    public Name(String value) {
        validateBlank(value);
        this.value = value;
    }

    private void validateBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidPlayerNameException();
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
