package blackjack.dto;

import blackjack.domain.GameResult;

public class PlayerGameResultResponse {
    private final String name;
    private final GameResult gameResult;

    public PlayerGameResultResponse(String name, GameResult gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public String getName() {
        return name;
    }

    public String getGameResult() {
        return gameResult.getName();
    }
}
