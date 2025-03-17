package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static List<GameResult> getAllGameResults() {
        return Arrays.stream(GameResult.values()).toList();
    }

    public static GameResult swapGameResult(final GameResult gameResult) {
        if (Objects.equals(gameResult, WIN)) {
            return LOSE;
        }
        if (Objects.equals(gameResult, LOSE)) {
            return WIN;
        }
        return DRAW;
    }

    public static GameResult findByScores(int score, int compareScore) {
        if (score > compareScore) {
            return WIN;
        }
        if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

}
