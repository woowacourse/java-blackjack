package blackjack.domain.gambler;

import java.util.Objects;

public class Name {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_NAME_LENGTH = 2;
    private static final String INVALID_PLAYER_NAME_LENGTH = "플레이어의 이름은 최소 2글자, 최대 5글자로 제한 한다.";
    private static final String INVALID_PLAYER_NAME_FORMAT = "플레이어의 이름에 공백을 포함할 수 없다.";
    private static final String SPACE = " ";

    private final String name;

    public Name(final String name) {
        this.name = validate(name);
    }

    private String validate(final String name) {
        validateForm(name);
        return name;
    }

    private void validateForm(final String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_LENGTH);
        }
        if (name.contains(SPACE)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_FORMAT);
        }
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
