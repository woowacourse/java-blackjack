package blackjack.strategy.profit;

import java.math.BigDecimal;

public class DrawStrategy implements ProfitStrategy {

    @Override
    public BigDecimal calculate(BigDecimal betting) {
        return BigDecimal.ZERO;
    }
}
