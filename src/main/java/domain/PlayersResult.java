package domain;

import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, WinState> result;

    public PlayersResult() {
        this.result = new LinkedHashMap<>();
    }

    public void addResult(Player player, WinState winState) {
        result.put(player, winState);
    }

    public int countWinState(WinState winState) {
        return (int) result.values().stream()
                .filter(value -> value.equals(winState))
                .count();
    }

    public Map<Player, WinState> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
