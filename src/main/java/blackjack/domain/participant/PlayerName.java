package blackjack.domain.participant;

import java.util.Objects;

public record PlayerName(String value) {

    @Override
    public boolean equals(Object o) {
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
