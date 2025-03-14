package result;

import participant.Player;
import java.util.Map;
import java.util.Set;

public class PlayerResult {
    private final Map<Player, GameStatus> gameResults;

    public PlayerResult(Map<Player, GameStatus> gameResults) {
        this.gameResults = gameResults;
    }

    public int calculateStatusCount(GameStatus status) {
        return (int) getAllPlayers().stream()
                .filter(player -> status.isEqualTo(gameResults.get(player)))
                .count();
    }

    public Set<Player> getAllPlayers() {
        return gameResults.keySet();
    }

    public GameStatus getGameResultStatus(Player key) {
        return gameResults.get(key);
    }
}
