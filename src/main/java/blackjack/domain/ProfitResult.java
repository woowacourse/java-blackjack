package blackjack.domain;

import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Collections;
import java.util.Map;

public class ProfitResult {

    private static final int MINUS = -1;
    private static final double INIT_PROFIT = 0;

    private Map<String, Double> result;

    public ProfitResult(Map<String, Double> result) {
        this.result = result;
    }

    public void calculateProfit(Map<Player, MatchResult> matchResult, Players players) {
        this.result = players.calculateProfitByMatchResult(matchResult, result);
    }

    public double calculateDealerProfit() {
        return MINUS * result.values()
                .stream()
                .reduce(INIT_PROFIT, Double::sum);
    }

    public Map<String, Double> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
