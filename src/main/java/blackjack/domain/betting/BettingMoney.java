package blackjack.domain.betting;

import blackjack.domain.result.GameResult;

import java.math.BigDecimal;

public class BettingMoney {
    private final BigDecimal amount;

    public BettingMoney(int amount) {
        this(BigDecimal.valueOf(amount));
    }

    public BettingMoney(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal calculateProfit(final GameResult gameResult) {
        return (amount.multiply(gameResult.profitRate()));
    }
}
