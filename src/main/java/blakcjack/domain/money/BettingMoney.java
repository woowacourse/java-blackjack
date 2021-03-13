package blakcjack.domain.money;

import java.util.Objects;

public class BettingMoney {
    private final double value;

    public BettingMoney(final double value) {
        this.value = value;
    }

    public int toInt() {
        return (int) value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BettingMoney bettingMoney = (BettingMoney) o;
        return Double.compare(bettingMoney.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
