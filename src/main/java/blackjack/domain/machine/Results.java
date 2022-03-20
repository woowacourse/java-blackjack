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

    public void addResult(Player guest, Player dealer, Double money, MatchResults result) {
        double profit = result.calculateProfit(money);
        results.put(guest, profit);
        results.put(dealer, calculateDealerProfit(dealer, profit));
    }

    public Set<Player> getPlayers() {
        return results.keySet();
    }

    public Double getProfit(Player player) {
        return results.get(player);
    }

    private double calculateDealerProfit(Player dealer, double profit) {
        return results.get(dealer) + profit * -1;
    }
}
