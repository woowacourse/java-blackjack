package domain.player;

import java.util.Objects;

public class BetAmount {

    private final int betAmount;

    private BetAmount(final int betAmount) {
        this.betAmount = betAmount;
    }

    public static BetAmount from(final int betAmount) {
        return new BetAmount(betAmount);
    }

    public int getBetAmount() {
        return betAmount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BetAmount betAmount1 = (BetAmount) o;
        return betAmount == betAmount1.betAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount);
    }
}
