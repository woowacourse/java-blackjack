package blackjack.model;

import blackjack.model.participant.Participant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BettingResult {
    private static final double DEALER_BET_MONEY = 0.0;
    private final Map<String, Double> result;

    public BettingResult(Participant dealer, List<Participant> players) {
        this.result = createResult(dealer, players);
    }

    private Map<String, Double> createResult(Participant dealer, List<Participant> players) {
        Map<String, Double> result = initResult(dealer);
        for (Participant player : players) {
            double playerProfit = player.getProfit(dealer);
            result.put(player.getName(), playerProfit);
            result.merge(dealer.getName(), -playerProfit, Double::sum);
        }
        return result;
    }

    private Map<String, Double> initResult(Participant dealer) {
        Map<String, Double> result = new LinkedHashMap<>();
        result.put(dealer.getName(), DEALER_BET_MONEY);
        return result;
    }

    public Map<String, Double> getResult() {
        return result;
    }
}
