package domain.betting;

import domain.game.GameResult;
import domain.participant.Name;

public class CalculateProfit {
    private final BettingAmounts bettingAmounts;

    public CalculateProfit(BettingAmounts bettingAmounts) {
        this.bettingAmounts = bettingAmounts;
    }

    public Revenue calculate(Name name, GameResult gameResult) {
        BettingAmount amount = bettingAmounts.getAmount(name);
        return new Revenue(amount.getMoney().multiply(gameResult.getProfitRate()));
    }
}
