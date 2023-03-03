package view;

import domain.GameResult;

import java.util.Arrays;

public enum GameResultMapper {

    LOSE(GameResult.LOSE, "패"),
    WIN(GameResult.WIN, "승"),
    DRAW(GameResult.DRAW, "무");

    private final GameResult gameResult;
    private final String message;

    GameResultMapper(GameResult gameResult, String message) {
        this.gameResult = gameResult;
        this.message = message;
    }

    public static String getGameResult(GameResult targetGameResult) {
        return Arrays.stream(values())
                .filter(gameResultMapper -> gameResultMapper.gameResult == targetGameResult)
                .map(gameResultMapper -> gameResultMapper.message)
                .findAny()
                .orElseThrow();
    }
}
