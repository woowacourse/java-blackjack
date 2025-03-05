package domain;

import java.util.HashMap;
import java.util.Map;

public class GameResult {

    private final Map<GameStatus, Integer> gameResult;

    public GameResult() {
        this.gameResult = new HashMap<>();
        for (GameStatus gameStatus : GameStatus.values()) {
            this.gameResult.put(gameStatus, 0);
        }
    }

    public void addStatusCount(GameStatus status) {
        gameResult.put(status, gameResult.get(status) + 1);
    }

    public int getStatusCount(GameStatus status) {
        return gameResult.get(status);
    }

    public Map<GameStatus, Integer> getGameResult() {
        return gameResult;
    }
}
