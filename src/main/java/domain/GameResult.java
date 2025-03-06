package domain;

import java.util.Map;
import java.util.Set;

public class GameResult {
    private final Map<Player, GameResultStatus> gameResults;

    public GameResult(Map<Player, GameResultStatus> gameResults) {
        this.gameResults = gameResults;
    }

    public int calculateWinCount() {
        return (int) gameResults.keySet().stream()
                .filter(participant -> gameResults.get(participant) == GameResultStatus.WIN)
                .count();
    }

    public int calculateLoseCount() {
        return (int) gameResults.keySet().stream()
                .filter(participant -> gameResults.get(participant) == GameResultStatus.LOSE)
                .count();
    }

    public Set<Player> getAllPlayers() {
        return gameResults.keySet();
    }

    public GameResultStatus getGameResultstatus(Player key) {
        return gameResults.get(key);
    }
}
