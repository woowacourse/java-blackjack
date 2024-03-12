package domain.game;

import domain.user.Player;

import java.util.Collections;
import java.util.Map;

public class PlayerResults {
    private final Map<Player, Result> playerResults;

    public PlayerResults(Map<Player, Result> playerResults) {
        this.playerResults = playerResults;
    }

    public Map<Player, Result> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }
}
