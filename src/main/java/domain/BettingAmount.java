package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingAmount {

    private static final String BLACKJACK_BONUS_RATE = "1.5";

    private BigDecimal amount;

    private BettingAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static BettingAmount of(BigDecimal amount) {
        return new BettingAmount(amount);
    }

    public void applyBlackjackBonus() {
        amount = amount.multiply(new BigDecimal(BLACKJACK_BONUS_RATE));
    }

    public void applyLoseAmount() {
        amount = amount.negate();
    }

    public void applyDrawAmount() {
        amount = BigDecimal.ZERO;
    }

    public BigDecimal value() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
