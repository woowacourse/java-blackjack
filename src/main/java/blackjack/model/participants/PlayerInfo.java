package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import blackjack.model.blackjackgame.Profit;

public final class PlayerInfo {
    private final Name name;
    private final Betting betting;

    public PlayerInfo(final Name name, final Betting betting) {
        this.name = name;
        this.betting = betting;
    }

    public Profit getProfit(final PlayerProfitCalculator playerProfitCalculator) {
        return betting.getProfit(playerProfitCalculator);
    }

    public Name getName() {
        return name;
    }
}
