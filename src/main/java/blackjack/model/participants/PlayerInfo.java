package blackjack.model.participants;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import blackjack.model.blackjackgame.Profit;

public final class PlayerInfo {
    private final String name;
    private final Betting betting;

    public PlayerInfo(final String name, final Betting betting) {
        this.name = name;
        this.betting = betting;
    }

    public Profit getProfit(final PlayerProfitCalculator playerProfitCalculator) {
        return betting.getProfit(playerProfitCalculator);
    }

    public String getName() {
        return name;
    }
}
