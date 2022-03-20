package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingResult {

    private final Map<Player, PlayerResult> result;

    public BettingResult(Map<Player, PlayerResult> result) {
        this.result = result;
    }

    public Map<String, Double> getPlayerResult() {
        return calculateResult();
    }

    public double getDealerResult() {
        double total = 0;

        for (double value : calculateResult().values()) {
            total += (value * -1);
        }

        return total;
    }


    private Map<String, Double> calculateResult() {
        Map<String, Double> playersProfit = new HashMap<>();

        for (Map.Entry<Player, PlayerResult> entry : result.entrySet()) {
            double profit = entry.getKey().getBettingAmount() * entry.getValue().getProfitRate();
            playersProfit.put(entry.getKey().getName(), profit);
        }

        return playersProfit;
    }
}
