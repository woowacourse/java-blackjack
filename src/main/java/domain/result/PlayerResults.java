package domain.result;

import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResults {
    private final Map<Player, Result> results;

    public PlayerResults(final Map<Player, Result> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public Map<Player, Result> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
