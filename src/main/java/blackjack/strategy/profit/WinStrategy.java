package blackjack.strategy.profit;

import java.math.BigDecimal;

public class WinStrategy implements ProfitStrategy {

    @Override
    public BigDecimal calculate(final BigDecimal betting) {
        return betting;
    }
}
