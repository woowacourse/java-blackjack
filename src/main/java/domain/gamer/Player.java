package domain.gamer;

import domain.calculatestrategy.PlayerStrategy;

public class Player extends Gamer {

    private final Betting betting;

    public Player(final Nickname nickname, final Betting betting) {
        super(nickname, new PlayerStrategy());
        this.betting = betting;
    }

    public int winBetting(final int profit) {
        if (isBlackJack()) {
            return betting.winBlackJackBetting(profit);
        }
        return betting.winBetting(profit);
    }

    public int loseBetting(final int profit) {
        return betting.loseBetting(profit);
    }
}
