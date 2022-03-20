package blackjack.domain.machine;

import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MatchResults {

    private final Map<Player, Double> results;

    public MatchResults(Player dealer) {
        this.results = new LinkedHashMap<>();
        this.results.put(dealer, (double) 0);
    }

    public void addResult(Player guest, Player dealer, double profit) {
        putGuestProfit(guest, profit);
        updateDealerProfit(dealer, profit);
    }

    public Set<Player> getPlayers() {
        return results.keySet();
    }

    public Double getProfit(Player player) {
        return results.get(player);
    }

    private void putGuestProfit(Player guest, double profit) {
        results.put(guest, profit);
    }

    private void updateDealerProfit(Player dealer, double profit) {
        results.put(dealer, calculateDealerProfit(dealer, profit));
    }

    private double calculateDealerProfit(Player dealer, double profit) {
        return results.get(dealer) + profit * -1;
    }
}
