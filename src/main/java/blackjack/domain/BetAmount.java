package blackjack.domain;

import java.util.Objects;

public class BetAmount {

    public static final BetAmount ZERO = new BetAmount(0);
    private static final int MIN_BET_AMOUNT = 1;
    private final int betAmount;

    public BetAmount(int betAmount) {
        this.betAmount = betAmount;
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
        return betAmount == betAmount1.betAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount);
    }
}