package blackjack.domain.bet;

import java.util.Objects;

public class Profit {

    private final int value;

    public Profit(int value) {
        this.value = value;
    }

    public Profit add(Profit other) {
        return new Profit(this.value + other.value);
    }

    public Profit inverse() {
        return new Profit(-1 * value);
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
