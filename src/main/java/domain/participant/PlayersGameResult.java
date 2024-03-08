package domain.participant;

import java.util.HashMap;
import java.util.Map;

import domain.GameResultStatus;

public class PlayersGameResult {

    private final Map<Player, GameResultStatus> result;

    public PlayersGameResult() {
        this.result = new HashMap<>();
    }

    public void put(final Player player, final GameResultStatus resultStatus) {
        result.put(player, resultStatus);
    }

    public Map<Player, GameResultStatus> getResult() {
        return Map.copyOf(result);
    }
}
