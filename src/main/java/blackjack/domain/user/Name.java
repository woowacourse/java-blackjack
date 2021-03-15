package blackjack.domain.user;

import java.util.Objects;

public class Name {

    private static final String INVALID_PLAYER_NAME_LENGTH_ERROR_MESSAGE = "이름은 1자 이상이어야 합니다.";

    private final String name;

    public Name(String name) {
        this.name = validateName(name);
    }

    private String validateName(String name) {
        if (isNull(name) || name.trim().length() < 1) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_LENGTH_ERROR_MESSAGE);
        }
        return name.trim();
    }

    private boolean isNull(String name) {
        return name == null;
    }

    public String getName() {
        return name;
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
}
