package blackjack.result;

import blackjack.GameRule;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum GameResult {

    WIN(100, "승"),
    BLACKJACK(50, "블랙잭"),
    LOSE(-100, "패"),
    DRAW(0, "무");

    private final int profitPercent;
    private final String description;

    GameResult(int profitPercent, String description) {
        this.profitPercent = profitPercent;
        this.description = description;
    }

    public static GameResult of(int playerScore, int dealerScore) {

        if (bothBust(playerScore, dealerScore)) {
            return LOSE;
        }

        return compareScores(playerScore, dealerScore);
    }

    public static Map<GameResult, Integer> count(List<GameResult> results) {
        Map<GameResult, Integer> gameResultToCount = new EnumMap<>(GameResult.class);

        for (GameResult result : GameResult.values()) {
            gameResultToCount.put(result, 0);
        }

        results.forEach(result ->
                gameResultToCount.merge(result, 1, Integer::sum));

        return gameResultToCount;
    }

    private static boolean bothBust(int heroScore, int villainScore) {
        return GameRule.isBust(heroScore) && GameRule.isBust(villainScore);
    }

    private static GameResult compareScores(int playerScore, int dealerScore) {
        playerScore = GameRule.applyBustRule(playerScore);
        dealerScore = GameRule.applyBustRule(dealerScore);

        if (playerScore > dealerScore) {
            return WIN;
        }

        if (playerScore < dealerScore) {
            return LOSE;
        }

        return DRAW;
    }

    public GameResult oppose() {
        if (this == WIN || this == BLACKJACK) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public int getProfitPercent() {
        return profitPercent;
    }

    public String getDescription() {
        return description;
    }
}
