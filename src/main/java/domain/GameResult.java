package domain;

import java.util.HashMap;
import java.util.Map;

public class GameResult {

    private final String name;
    private final Map<GameStatus, Integer> gameResult;

    public GameResult(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public Map<GameStatus, Integer> getGameResult() {
        return gameResult;
    }
}
