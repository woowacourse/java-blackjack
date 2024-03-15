package domain.participant.player;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.BlackjackResultStatus;

public class PlayerResults {

    private final Map<Player, BlackjackResultStatus> results;

    public PlayerResults() {
        this.results = new LinkedHashMap<>();
    }

    public Players getPlayers() {
        return Players.from(results.keySet());
    }

    public void put(final Player player, final BlackjackResultStatus resultStatus) {
        results.put(player, resultStatus);
    }

    public BlackjackResultStatus statusOf(final Player player) {
        return results.get(player);
    }
}
