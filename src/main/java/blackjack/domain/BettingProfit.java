package blackjack.domain;

import blackjack.domain.participant.Participant;
import java.util.Map;

public class BettingProfit {

    private final Map<Participant, Long> playerProfit;
    private final long dealerProfit;

    public BettingProfit(Map<Participant, Long> playerProfit, Long dealerProfit) {
        this.playerProfit = playerProfit;
        this.dealerProfit = dealerProfit;
    }

    public long getDealerProfit() {
        return dealerProfit;
    }

    public Map<Participant, Long> getPlayerProfit() {
        return playerProfit;
    }
}
