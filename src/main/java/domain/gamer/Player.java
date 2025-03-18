package domain.gamer;

import domain.calculatestrategy.PlayerStrategy;

public class Player extends Gamer {

    private final Betting betting;

    public Player(final Nickname nickname, final Betting betting) {
        super(nickname, new PlayerStrategy());
        this.betting = betting;
    }

    public int calculateWinProfit(final int profit) {
        if (isBlackJack()) {
            return betting.calculateBlackjackWinProfit(profit);
        }
        return betting.calculateRegularWinProfit(profit);
    }

    public int calculateLoseProfit(final int profit) {
        return betting.calculateRegularLoseProfit(profit);
    }
}
