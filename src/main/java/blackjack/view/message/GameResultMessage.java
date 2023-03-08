package blackjack.view.message;


import blackjack.constants.ErrorCode;
import blackjack.domain.GameResult;
import blackjack.view.exception.MessageDoesNotExistException;
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
        throw new MessageDoesNotExistException(ErrorCode.NOT_EXIST_MESSAGE);
    }

    public String getMessage() {
        return message;
    }

    private GameResult getGameResult() {
        return gameResult;
    }
}
