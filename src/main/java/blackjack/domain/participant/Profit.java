package blackjack.domain.participant;

import java.math.BigDecimal;

public class Profit {

    private BigDecimal amount;

    private Profit(final BigDecimal amount) {
        this.amount = amount;
    }

    public static Profit initProfit() {
        return new Profit(BigDecimal.ZERO);
    }

    public void earn(final BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public void lose(final BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
