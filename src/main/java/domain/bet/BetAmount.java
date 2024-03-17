package domain.bet;

import java.util.Objects;

public final class BetAmount {

    public static final int MIN_BET_AMOUNT = 500;
    
    private final double value;

    public BetAmount(final double value) {
        validateMinBetAmount(value);
        this.value = value;
    }

    private void validateMinBetAmount(double value) {
        if (value < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 최소 500원입니다.");
        }
    }

    public double getValue() {
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
        BetAmount betAmount1 = (BetAmount) o;
        return value == betAmount1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
