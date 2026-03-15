package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Results {
    private final Map<Player, Result> results;

    public Results() {
        this.results = new LinkedHashMap<>();
    }

    public void addResult(Player player, Result result) {
        results.put(player, result);
    }

    public Result findByPlayer(Player player) {
        return results.get(player);
    }

    public Map<Player, Result> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
