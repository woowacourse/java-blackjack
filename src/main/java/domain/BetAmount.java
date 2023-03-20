package domain;

import java.util.Objects;

public class BetAmount {

    private static final int MIN_AMOUNT = 1000;
    private static final int MAX_AMOUNT = 1000000;
    protected static final String VALID_AMOUNT_MSG = String.format("베팅 금액은 %d원에서 %d원 사이여야합니다.",
        MIN_AMOUNT, MAX_AMOUNT);
    private static final BetAmount ZERO = new BetAmount(0);

    private final double betAmount;

    private BetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public static BetAmount of(double betAmount) {
        validateBetAmount(betAmount);
        return new BetAmount(betAmount);
    }

    private static void validateBetAmount(double betAmount) {
        if (betAmount < MIN_AMOUNT|| betAmount > MAX_AMOUNT) {
            throw new IllegalArgumentException(VALID_AMOUNT_MSG);
        }
    }

    public BetAmount multiply(double rate) {
        if (rate == 0) {
            return ZERO;
        }
        return new BetAmount(betAmount * rate);
    }

    public double toDouble() {
        return betAmount;
    }

    public static BetAmount getZero() {
        return ZERO;
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
        return Double.compare(betAmount1.betAmount, betAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount);
    }
}
