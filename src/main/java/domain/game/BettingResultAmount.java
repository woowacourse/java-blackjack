package domain.game;

import domain.participants.BettingAmount;

public class BettingResultAmount {
    private static final int NEUTRAL_RESULT = 0;

    private final int money;

    public BettingResultAmount(BettingAmount bettingAmount, GameResult gameResult) {
        this.money = calculateBettingResult(bettingAmount, gameResult);
    }

    public int getMoney() {
        return money;
    }

    private int calculateBettingResult(BettingAmount bettingAmount, GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return bettingAmount.getMoney();
        }
        if (gameResult == GameResult.LOSE) {
            return -bettingAmount.getMoney();
        }
        return NEUTRAL_RESULT;
    }
}
