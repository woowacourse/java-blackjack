package blackjack.domain.player;

import java.util.Objects;

public final class Name {
    private static final int LENGTH_LOWER_BOUND = 1;
    private static final int LENGTH_UPPER_BOUND = 5;
    private static final String DEALER = "딜러";

    private final String value;

    private Name(final String value) {
        validate(value);
        this.value = value;
    }

    public static Name from(final String value) {
        if (DEALER.equals(value)) {
            throw new IllegalArgumentException("이름이 '" + DEALER + "'일 수 없습니다.");
        }
        return new Name(value);
    }

    public static Name createDealerName() {
        return new Name(DEALER);
    }

    private void validate(final String value) {
        if (isValidName(value)) {
            throw new IllegalArgumentException(
                    "이름은 " + LENGTH_LOWER_BOUND + "자 이상, " + LENGTH_UPPER_BOUND + "자 이하여아 합니다. 입력값:" + value
            );
        }
    }

    private boolean isValidName(final String value) {
        return value == null || value.length() < LENGTH_LOWER_BOUND || LENGTH_UPPER_BOUND < value.length();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }
}
