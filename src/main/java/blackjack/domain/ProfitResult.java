package blackjack.domain;

import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Collections;
import java.util.Map;

public class ProfitResult {

    private Map<String, Double> result;

    public ProfitResult(Map<String, Double> result) {
        this.result = result;
    }

    public void calculateProfit(Map<Player, MatchResult> matchResult, Players players) {
        this.result = players.calculateProfitByMatchResult(matchResult, result);
    }

    public double calculateDealerProfit() {
        return -1 * result.values()
                .stream()
                .reduce(0.0, Double::sum);
    }

    public Map<String, Double> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
