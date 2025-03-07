package blackjack.domain.gambler;

import static blackjack.view.ErrorMessage.INVALID_PLAYER_NAME_FORMAT;
import static blackjack.view.ErrorMessage.INVALID_PLAYER_NAME_LENGTH;

import java.util.Objects;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_NAME_LENGTH = 2;
    private static final String SPACE = " ";

    private final String name;

    public Name(final String name) {
        this.name = validateForm(name);
    }

    private String validateForm(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_LENGTH.getMessage());
        }
        if (name.split(SPACE).length != 1) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_FORMAT.getMessage());
        }
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Name name1 = (Name) object;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
