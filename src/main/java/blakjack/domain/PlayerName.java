package blakjack.domain;

import java.util.Objects;

import static blakjack.domain.participant.Dealer.DEALER_NAME;

public final class PlayerName {
    private static final String INVALID_MESSAGE = "딜러라는 이름은 사용할 수 없습니다";

    private final String value;

    public PlayerName(final String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(INVALID_MESSAGE);
        }
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName that = (PlayerName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
