package domain;

import domain.gamer.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResults {
    private final Map<Player, Result> results;

    public PlayerResults() {
        this.results = new LinkedHashMap<>();
    }

    public void addResult(final Player player, final Result result) {
        results.put(player,result);
    }

    public Map<Player, Result> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
