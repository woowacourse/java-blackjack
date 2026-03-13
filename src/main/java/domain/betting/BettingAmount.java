package domain.betting;

import java.util.Objects;

public class BettingAmount {
    private final int bettingAmount;

    public BettingAmount(int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingAmount that = (BettingAmount) o;
        return bettingAmount == that.bettingAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingAmount);
    }
}
