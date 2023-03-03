package view;

import domain.GameResult;

public class PlayerScore {

    private final String name;
    private final GameResult gameResult;

    public PlayerScore(String name, GameResult gameResult) {
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
