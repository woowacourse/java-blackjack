package view.message;

import domain.game.GameResult;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameResultMessage {

    WIN(GameResult.WIN, "승"),
    LOSE(GameResult.LOSE, "패"),
    DRAW(GameResult.DRAW, "무");

    private static final Map<GameResult, String> CACHE = Stream.of(GameResultMessage.values())
            .collect(Collectors.toUnmodifiableMap(resultMessage -> resultMessage.gameResult,
                    resultMessage -> resultMessage.message));

    private final GameResult gameResult;
    private final String message;

    GameResultMessage(final GameResult gameResult, final String message) {
        this.gameResult = gameResult;
        this.message = message;
    }

    public static String findMessage(final GameResult gameResult) {
        return CACHE.get(gameResult);
    }
}
