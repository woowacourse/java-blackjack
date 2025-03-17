package domain.game;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.participants.PlayerName;
import java.util.Collections;
import java.util.Map;

public class GameStatistics {
    private final Map<PlayerName, GameResult> results;

    public GameStatistics(Map<PlayerName, GameResult> results) {
        this.results = results;
    }

    public int getDealerWinCount() {
        return getDealerResultCount(LOSE);
    }

    public int getDealerLoseCount() {
        return getDealerResultCount(WIN);
    }

    public int getDealerDrawCount() {
        return getDealerResultCount(DRAW);
    }

    private int getDealerResultCount(GameResult gameResult) {
        return (int) results.values().stream()
                .filter(result -> result == gameResult)
                .count();
    }

    public Map<PlayerName, GameResult> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
