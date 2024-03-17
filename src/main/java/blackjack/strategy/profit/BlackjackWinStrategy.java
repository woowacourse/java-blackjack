package blackjack.strategy.profit;

import java.math.BigDecimal;

public class BlackjackWinStrategy implements ProfitStrategy {

    public static final double BLACKJACK_WEIGHT = 1.5;

    @Override
    public BigDecimal calculate(BigDecimal betting) {
        return betting.multiply(BigDecimal.valueOf(BLACKJACK_WEIGHT));
    }
}
