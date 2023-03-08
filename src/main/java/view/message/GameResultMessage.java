package view.message;

import domain.game.Result;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameResultMessage {
    WIN(Result.WIN, "승"),
    LOSE(Result.LOSE, "패"),
    DRAW(Result.DRAW, "무");

    private static final Map<Result, String> CACHE = Stream.of(GameResultMessage.values())
            .collect(Collectors.toUnmodifiableMap(resultMessage -> resultMessage.result,
                    resultMessage -> resultMessage.message));

    private final Result result;
    private final String message;

    GameResultMessage(final Result result, final String message) {
        this.result = result;
        this.message = message;
    }

    public static String findMessage(final Result result) {
        return CACHE.get(result);
    }
}
