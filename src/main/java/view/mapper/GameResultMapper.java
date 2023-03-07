package view.mapper;

import domain.game.GameResult;

import java.util.Arrays;

public enum GameResultMapper {

    LOSE(GameResult.LOSE, "패"),
    WIN(GameResult.WIN, "승"),
    DRAW(GameResult.DRAW, "무");

    private static final String NO_SUCH_GAME_RESULT_MESSAGE = "[ERROR] 게임 결과가 정의되어 있지 않습니다.";

    private final GameResult gameResult;
    private final String message;

    GameResultMapper(final GameResult gameResult, final String message) {
        this.gameResult = gameResult;
        this.message = message;
    }

    public static String getGameResult(final GameResult targetGameResult) {
        return Arrays.stream(values())
                .filter(gameResultMapper -> gameResultMapper.gameResult == targetGameResult)
                .map(gameResultMapper -> gameResultMapper.message)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_GAME_RESULT_MESSAGE));
    }
}
