package blackjack.domain.participant;

import java.math.BigDecimal;

public class Profit {

    private final BigDecimal amount;
    private final boolean isMinus;

    private Profit(final BigDecimal amount, final boolean isMinus) {
        this.amount = amount;
        this.isMinus = isMinus;
    }

    public static Profit initProfit() {
        return new Profit(new BigDecimal(0), false);
    }

    public String getProfit() {
        if (isMinus) {
            return "-" + amount;
        }
        return String.valueOf(amount);
    }
}
