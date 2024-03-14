package blackjack.strategy.profit;

import java.math.BigDecimal;

public interface ProfitStrategy {

    BigDecimal calculate(BigDecimal betting);
}
