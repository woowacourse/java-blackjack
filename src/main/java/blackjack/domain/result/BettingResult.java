package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingResult {

    private final Map<Player, PlayerResult> result;

    public BettingResult(Map<Player, PlayerResult> result) {
        this.result = result;
    }

    public Map<Player, Profit> getPlayerResult() {
        return calculateProfit();
    }

    public Profit getDealerResult() {
        return Profit.calculateDealerProfit(calculateProfit());
    }

    private Map<Player, Profit> calculateProfit() {
        Map<Player, Profit> playersProfit = new HashMap<>();

        for (Map.Entry<Player, PlayerResult> entry : result.entrySet()) {
            Profit profit = Profit.calculatePlayerProfit(entry.getKey(), entry.getValue());
            playersProfit.put(entry.getKey(), profit);
        }

        return playersProfit;
    }
}
