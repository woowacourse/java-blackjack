package domain.betting;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingAmount {

    private final BigDecimal bettingAmount;

    public BettingAmount(BigDecimal bettingAmount) {
        this.bettingAmount = bettingAmount.stripTrailingZeros();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) o;
        return this.bettingAmount.compareTo(that.bettingAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingAmount);
    }

    public BigDecimal getBettingAmount() {
        return bettingAmount;
    }
}
