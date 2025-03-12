package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(final String description) {
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }
}
