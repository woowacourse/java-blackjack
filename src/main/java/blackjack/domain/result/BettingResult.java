package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingResult {

    private final Map<String, Double> result;

    private BettingResult(Map<String, Double> result) {
        this.result = result;
    }

    public static BettingResult of(Map<Player, PlayerResult> result) {
        Map<String, Double> playersProfit = new HashMap<>();

        for (Map.Entry<Player, PlayerResult> entry : result.entrySet()) {
            double profit = entry.getKey().getBettingAmount() * entry.getValue().getProfitRate();
            playersProfit.put(entry.getKey().getName(), profit);
        }

        return new BettingResult(playersProfit);
    }

    public Map<String, Double> getPlayerResult() {
        return result;
    }

    public double getDealerResult() {
        double total = 0;

        for (Double value : result.values()) {
            total += value * -1;
        }

        return total;
    }
}
