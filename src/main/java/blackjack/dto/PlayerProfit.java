package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerProfit {

    private final String name;
    private final int profit;

    private PlayerProfit(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static PlayerProfit of(final Player player, final Dealer dealer) {
        return new PlayerProfit(player.getName(), player.profit(dealer));
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
