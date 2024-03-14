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
        results.put(player, result);
    }

    public void win(final Player player) {
        results.put(player, Result.WIN);
    }

    public void tie(final Player player) {
        results.put(player, Result.TIE);
    }

    public void lose(final Player player) {
        results.put(player, Result.LOSE);
    }

    public Map<Player, Result> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
