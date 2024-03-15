package dto;

import domain.gamer.Player;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerProfits {
    private final Map<Player, BigDecimal> results;

    public PlayerProfits() {
        this.results = new LinkedHashMap<>();
    }

    public void addResult(final Player player, final BigDecimal profit) {
        results.put(player, profit);
    }

    public Map<Player, BigDecimal> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
