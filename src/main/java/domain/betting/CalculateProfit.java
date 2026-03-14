package domain.betting;

import domain.game.GameResult;
import domain.participant.Name;

public class CalculateProfit {
    private final Revenue revenue;
    private final BettingManager bettingManager;

    public CalculateProfit(Revenue revenue, BettingManager bettingManager) {
        this.revenue = revenue;
        this.bettingManager = bettingManager;
    }

    public Revenue calculate(Name name, GameResult gameResult) {
        BettingAmount amount = bettingManager.getAmount(name);
        return calculateRevenue(gameResult, amount);
    }

    private static Revenue calculateRevenue(GameResult gameResult, BettingAmount amount) {
        if (gameResult == GameResult.BLACKJACK) {
            return new Revenue(amount.getMoney() / 2);
        }
        if (gameResult == GameResult.WIN) {
            return new Revenue(amount.getMoney());
        }
        if (gameResult == GameResult.LOSE) {
            return new Revenue(-amount.getMoney());
        }
        return new Revenue(0);
    }
}
