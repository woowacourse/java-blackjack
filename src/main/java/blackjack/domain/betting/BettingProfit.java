package blackjack.domain.betting;

import blackjack.domain.participant.Player;
import java.util.Map;

public class BettingProfit {

    private final Map<Player, Long> playerProfit;
    private final long dealerProfit;

    public BettingProfit(Map<Player, Long> playerProfit, Long dealerProfit) {
        this.playerProfit = playerProfit;
        this.dealerProfit = dealerProfit;
    }

    public long getDealerProfit() {
        return dealerProfit;
    }

    public Map<Player, Long> getPlayerProfit() {
        return Map.copyOf(playerProfit);
    }
}
