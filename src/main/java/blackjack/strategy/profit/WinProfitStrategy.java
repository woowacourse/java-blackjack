package blackjack.strategy.profit;

import java.math.BigDecimal;

public class WinProfitStrategy implements ProfitStrategy {

    @Override
    public BigDecimal calculate(final BigDecimal betting) {
        return betting;
    }
}
