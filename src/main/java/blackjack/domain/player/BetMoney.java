package blackjack.domain.player;

import blackjack.utils.InputValidator;

import java.util.Objects;

public class BetMoney {

    private static final int MINIMUM_BET_MONEY = 1;

    private final double value;

    public BetMoney(double value) {
        validateNumber(value);
        this.value = value;
    }

    private void validateNumber(double value) {
        if (value < MINIMUM_BET_MONEY) {
            throw new IllegalStateException(InputValidator.MINIMUM_MONEY_ERROR_MESSAGE);
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetMoney betMoney = (BetMoney) o;
        return Double.compare(betMoney.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
