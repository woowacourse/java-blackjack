package blackjack.domain.betting;

import blackjack.domain.result.GameResult;

public class BettingMoney {
    private final int amount;

    public BettingMoney(int amount) {
        this.amount = amount;
    }

    public int calculateProfit(final GameResult gameResult) {
        return (int) (amount * gameResult.profitRate());
    }
}
