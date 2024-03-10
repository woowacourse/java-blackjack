package domain;

import domain.gamer.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerResults {
    private final Map<Player, Result> results;

    public PlayerResults(final Map<Player, Result> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public int findWinCount() {
        return (int) results.values().stream().filter(result -> result.equals(Result.WIN)).count();
    }

    public int findLoseCount() {
        return (int) results.values().stream().filter(result -> result.equals(Result.LOSE)).count();
    }

    public int findTieCount() {
        return (int) results.values().stream().filter(result -> result.equals(Result.TIE)).count();
    }

    public Map<Player, Result> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
