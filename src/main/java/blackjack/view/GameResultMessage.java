package blackjack.view;


import blackjack.domain.GameResult;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameResultMessage {
    WIN(GameResult.WIN, "승"),
    LOSE(GameResult.LOSE, "패");

    private static final Map<GameResult, GameResultMessage> GAME_RESULT_MESSAGE = Arrays.stream(values())
            .collect(Collectors.toMap(GameResultMessage::getGameResult, Function.identity()));

    private final GameResult gameResult;
    private final String message;

    GameResultMessage(GameResult gameResult, String message) {
        this.gameResult = gameResult;
        this.message = message;
    }

    public static GameResultMessage from(GameResult gameResult) {
        if (GAME_RESULT_MESSAGE.containsKey(gameResult)) {
            return GAME_RESULT_MESSAGE.get(gameResult);
        }
        throw new IllegalArgumentException("해당 결과가 존재하지 않습니다");
    }

    public String getMessage() {
        return message;
    }

    private GameResult getGameResult() {
        return gameResult;
    }
}
