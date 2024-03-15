package blackjack.domain.bet;

import java.util.Objects;

public class Profit {

    private final int value;

    public Profit(int value) {
        this.value = value;
    }

    public int getValue() {
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
        Profit profit = (Profit) o;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
