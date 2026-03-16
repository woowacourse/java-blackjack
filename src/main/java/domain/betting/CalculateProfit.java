package domain.betting;

import domain.game.GameResult;
import domain.participant.Name;
import java.math.BigDecimal;

public class CalculateProfit {
    private static final BigDecimal PROFIT_RATE = new BigDecimal("1.5");
    private final BettingAmounts bettingAmounts;

    public CalculateProfit(BettingAmounts bettingAmounts) {
        this.bettingAmounts = bettingAmounts;
    }

    public Revenue calculate(Name name, GameResult gameResult) {
        BettingAmount amount = bettingAmounts.getAmount(name);
        return new Revenue(amount.getMoney().multiply(gameResult.getProfitRate()));
    }

    private static Revenue calculateRevenue(GameResult gameResult, BettingAmount amount) {
        if (gameResult == GameResult.BLACKJACK) {
            return new Revenue(amount.getMoney().multiply(PROFIT_RATE));
        }
        if (gameResult == GameResult.WIN) {
            return new Revenue(amount.getMoney());
        }
        if (gameResult == GameResult.LOSE) {
            return new Revenue(amount.getMoney().negate());
        }
        return new Revenue(BigDecimal.ZERO);
    }
}
