package domain;

import java.util.Map;
import java.util.Set;

public class ProfitResults {

    private final Map<Player, Profit> profitResults;

    public ProfitResults(Map<Player, Profit> profitResults) {
        this.profitResults = profitResults;
    }

    public Set<Player> getAllPlayers() {
        return profitResults.keySet();
    }

    public int getProfitValue(Player key) {
        return profitResults.get(key).value();
    }

    public int calculateDealerProfit() {
        return -profitResults.values().stream()
                .mapToInt(Profit::value)
                .sum();
    }
}
