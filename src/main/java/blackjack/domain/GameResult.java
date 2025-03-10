package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public static GameResult of(int dealerSumOfCards, int playerSumOfCards) {
        if (dealerSumOfCards < playerSumOfCards) {
            return WIN;
        }

        if (dealerSumOfCards > playerSumOfCards) {
            return LOSE;
        }

        return DRAW;
    }

    public static Map<GameResult, Integer> getDealerFormat() {
        Map<GameResult, Integer> gameResultToCount = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            gameResultToCount.put(gameResult, 0);
        }

        return gameResultToCount;
    }

    public GameResult oppose() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
