package domain;

import java.math.BigDecimal;

public class BettingAmount {

    private BigDecimal amount;

    private BettingAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static BettingAmount of(BigDecimal amount) {
        return new BettingAmount(amount);
    }

    public void applyBlackjackBonus() {
        amount = amount.multiply(new BigDecimal("1.5"));
    }

    public void applyLoseAmount() {
        amount = amount.negate();
    }

    public BigDecimal value() {
        return amount;
    }
}
