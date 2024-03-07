package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResults {
    private final Map<Player, Result> results;

    public PlayerResults(final Map<Player, Result> results) {
        this.results = Map.copyOf(new LinkedHashMap<>(results));
    }


    public Map<Player, Result> getResults() {
        return results;
    }
}
