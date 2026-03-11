package blackjack.domain;

import blackjack.domain.participant.Participant;
import java.util.Map;

public class BettingProfit {

    private final Map<Participant, Integer> playerProfit;
    private final int dealerProfit;

    public BettingProfit(Map<Participant, Integer> playerProfit, int dealerProfit) {
        this.playerProfit = playerProfit;
        this.dealerProfit = dealerProfit;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public Map<Participant, Integer> getPlayerProfit() {
        return playerProfit;
    }
}
