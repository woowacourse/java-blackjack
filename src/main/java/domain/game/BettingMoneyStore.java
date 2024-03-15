package domain.game;

import domain.constant.GameResult;
import domain.participant.PlayerName;

import java.util.Map;

public class BettingMoneyStore {
    private static final int ZERO_MONEY = 0;
    private static final double BLACKJACK_REVENUE_PERCENTAGE = 1.5;

    private final Map<PlayerName, BettingMoney> bettingMoney;

    public BettingMoneyStore(final Map<PlayerName, BettingMoney> bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public int getPlayerRevenue(final PlayerName playerName, final GameResult gameResult) {
        if (gameResult == GameResult.LOSE) {
            return -(getBettingMoneyValue(playerName));
        }

        if (gameResult == GameResult.DRAW) {
            return ZERO_MONEY;
        }

        return countWinningRevenue(getBettingMoneyValue(playerName), gameResult);
    }

    private int getBettingMoneyValue(final PlayerName playerName) {
        return bettingMoney.get(playerName)
                .value();
    }

    private int countWinningRevenue(final int bettingMoney, final GameResult gameResult) {
        if (gameResult == GameResult.BLACKJACK_WIN) {
            return (int) (bettingMoney * BLACKJACK_REVENUE_PERCENTAGE);
        }

        return bettingMoney;
    }
}
