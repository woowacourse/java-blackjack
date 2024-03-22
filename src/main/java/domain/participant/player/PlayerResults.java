package domain.participant.player;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.result.Profit;

public class PlayerResults {

    private final Map<Player, Profit> results;

    public PlayerResults() {
        this.results = new LinkedHashMap<>();
    }

    public Players getPlayers() {
        return Players.from(results.keySet());
    }

    public void put(final Player player, final Profit profit) {
        results.put(player, profit);
    }

    public Profit profitOf(final Player player) {
        return results.get(player);
    }
}
