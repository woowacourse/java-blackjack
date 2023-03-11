package view;

import domain.GameResult;

public class PlayerResult {

    private final String name;
    private final GameResult gameResult;

    public PlayerResult(String name, GameResult gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public String getName() {
        return name;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
