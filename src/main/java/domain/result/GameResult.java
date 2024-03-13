package domain.result;

import domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, GameResultStatus> result;

    public GameResult() {
        this(new LinkedHashMap<>());
    }

    public GameResult(final Map<Player, GameResultStatus> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public void put(final Player player, final GameResultStatus resultStatus) {
        result.put(player, resultStatus);
    }

    public Map<Player, GameResultStatus> getResult() {
        return Map.copyOf(result);
    }
}
