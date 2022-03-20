package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class ParticipantProfit {

    private final String name;
    private final int profit;

    private ParticipantProfit(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ParticipantProfit createDealerProfit(final Dealer dealer, final Players players) {
        return new ParticipantProfit(dealer.getName(), players.dealerProfit(dealer));
    }

    public static ParticipantProfit createPlayerProfit(final Player player, final Dealer dealer) {
        return new ParticipantProfit(player.getName(), player.profit(dealer));
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
