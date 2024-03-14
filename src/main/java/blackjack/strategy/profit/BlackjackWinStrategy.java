package blackjack.strategy.profit;

import java.math.BigDecimal;

public class BlackjackWinStrategy implements ProfitStrategy {

    @Override
    public BigDecimal calculate(BigDecimal betting) {
        return betting.multiply(BigDecimal.valueOf(1.5));
    }
}
