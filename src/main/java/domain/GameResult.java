package domain;

import java.util.Map;
import java.util.Set;

public class GameResult {
    private final Map<Participant, GameResultStatus> gameResults;

    public GameResult(Map<Participant, GameResultStatus> gameResults) {
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

    public Set<Participant> getAllParticipants() {
        return gameResults.keySet();
    }

    public GameResultStatus getGameResultstatus(Participant key) {
        return gameResults.get(key);
    }
}
