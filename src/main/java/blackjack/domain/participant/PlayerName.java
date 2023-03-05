package blackjack.domain.participant;

import java.util.Objects;

public class PlayerName {

    private static final String RESTRICT = "딜러";
    private static final int MAXIMUM_LENGTH = 10;

    private final String value;

    public PlayerName(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        validateBlank(value);
        validateLength(value);
        validateRestrict(value);
    }

    private void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다. 현재 이름: " + value);
        }
    }

    private void validateLength(final String value) {
        if (value.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("이름은 " + MAXIMUM_LENGTH + "글자 이하여야 합니다. 현재 이름: " + value);
        }
    }

    private void validateRestrict(final String value) {
        if (Objects.equals(RESTRICT, value)) {
            throw new IllegalArgumentException("이름은 " + RESTRICT + "일 수 없습니다. 현재 이름: " + value);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerName playerName = (PlayerName) o;
        return Objects.equals(value, playerName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
