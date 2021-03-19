package blakcjack.domain.money;

import blakcjack.domain.outcome.Outcome;

import java.util.Objects;

public class BettingMoney {
    private final double value;

    public BettingMoney(final double value) {
        this.value = value;
    }

    public int toEarning(final Outcome outcome, final boolean isBlackJack) {
        final EarningRate earningRate = EarningRate.of(outcome, isBlackJack);
        return (int) (value * earningRate.getRate());
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
