package blackjack.domain.machine;

import blackjack.domain.machine.result.MatchResults;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Results {

    private final Map<Player, Double> results;

    public Results(Player dealer) {
        this.results = new LinkedHashMap<>();
        this.results.put(dealer, (double) 0);
    }

    public void addResult(Player dealer, Map<Player, Double> bettingBox, MatchResults result) {
        for (Player player : bettingBox.keySet()) {
            Double money = bettingBox.get(player);
            double profit = result.calculateProfit(money);
            results.put(player, profit);
            results.put(dealer, results.get(dealer) + profit * -1);
        }
    }

    public Set<Player> getPlayers() {
        return results.keySet();
    }

    public Double getProfit(Player player) {
        return results.get(player);
    }
}
