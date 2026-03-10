package blackjack.dto;

import blackjack.domain.GameResult;
import java.util.Map;

public record GameResultDisplayName(String displayName) {

    private static final Map<GameResult, String> GAME_RESULT_NAMES = Map.of(
            GameResult.WIN, "승",
            GameResult.DRAW, "무",
            GameResult.LOSE, "패"
    );

    public static GameResultDisplayName from(GameResult gameResult) {
        return new GameResultDisplayName(GAME_RESULT_NAMES.get(gameResult));
    }
}
