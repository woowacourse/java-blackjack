package blackjack.dto;

import blackjack.domain.paticipant.Dealer;
import blackjack.domain.paticipant.Player;

public class PlayerProfit {

    private final String name;
    private final double profit;

    private PlayerProfit(final String name, final double profit) {
        this.name = name;
        this.profit = profit;
    }

    public static PlayerProfit of(final Player player, final Dealer dealer) {
        return new PlayerProfit(player.getName(), player.profit(dealer));
    }

    public String getName() {
        return name;
    }

    public double getProfit() {
        return profit;
    }
}
