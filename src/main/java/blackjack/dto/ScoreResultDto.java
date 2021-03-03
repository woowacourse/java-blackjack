package blackjack.dto;

import blackjack.domain.GameResult;

public class ScoreResultDto {
    private final String name;
    private final GameResult gameResult;

    public ScoreResultDto(String name, GameResult gameResult) {
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
