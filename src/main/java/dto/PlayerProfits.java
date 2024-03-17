package dto;

import domain.Money;
import domain.gamer.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerProfits {
    private final Map<Player, Money> results;

    public PlayerProfits() {
        this.results = new LinkedHashMap<>();
    }

    public void addResult(final Player player, final Money profit) {
        results.put(player, profit);
    }

    public Map<Player, Money> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
