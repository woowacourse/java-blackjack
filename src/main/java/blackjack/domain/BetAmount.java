package blackjack.domain;

import java.util.Objects;

public class BetAmount {

    public static final BetAmount ZERO = new BetAmount(0);

    private final double betAmount;

    public BetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public BetAmount toNegative() {
        return new BetAmount(betAmount * -1);
    }

    public BetAmount toHalf() {
        return new BetAmount(betAmount / 2);
    }

    public BetAmount add(BetAmount other) {
        return new BetAmount(betAmount + other.betAmount);
    }

    public double getBetAmount() {
        return betAmount;
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

    @Override
    public String toString() {
        return "BetAmount{" +
                "betAmount=" + betAmount +
                '}';
    }
}