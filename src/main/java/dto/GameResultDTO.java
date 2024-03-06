package dto;

import domain.GameResult;

public class GameResultDTO {
    private final String gamerName;
    private final GameResult gameResult;

    public GameResultDTO(String gamerName, GameResult gameResult) {
        this.gamerName = gamerName;
        this.gameResult = gameResult;
    }

    public String getGamerName() {
        return gamerName;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
