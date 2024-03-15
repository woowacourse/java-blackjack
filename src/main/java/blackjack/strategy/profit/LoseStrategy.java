package blackjack.strategy.profit;

import java.math.BigDecimal;

public class LoseStrategy implements ProfitStrategy {

    private static final int LOSE_WEIGHT = -1;

    @Override
    public BigDecimal calculate(BigDecimal betting) {
        return betting.multiply(BigDecimal.valueOf(LOSE_WEIGHT));
    }
}
