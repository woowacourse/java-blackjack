package blackjack.domain.player;

import java.math.BigDecimal;
import java.util.Objects;

public class Profit {

    private final BigDecimal value;

    public Profit(BigDecimal value) {
        this.value = value;
    }

    public Profit(BetAmount betAmount) {
        this(new BigDecimal(String.valueOf(betAmount.value())));
    }

    public Profit multiply(double value) {
        return new Profit(this.value.multiply(BigDecimal.valueOf(value)));
    }

    public Profit add(Profit other) {
        return new Profit(this.value.add(other.value));
    }

    public Profit reverseSign() {
        return this.multiply(-1);
    }

    public BigDecimal getValue() {
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
        Profit other = (Profit) o;
        return value.compareTo(other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
