package domain.enums;

import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public static GameResult calculatePlayerResult(Player player, int dealerScore, boolean dealerBust) {
        int playerScore = player.getScore();

        if (player.isBust() || (playerScore < dealerScore && !dealerBust)) {
            return GameResult.LOSE;
        }
        if (playerScore == dealerScore) {
            return GameResult.DRAW;
        }
        return GameResult.WIN;
    }

    public static Map<GameResult, Integer> calculateDealerResult(List<GameResult> playerGameResults) {
        Map<GameResult, Integer> results = new HashMap<>();
        playerGameResults.forEach(
                result -> {
                    GameResult dealerGameResult = GameResult.getOpposite(result);
                    results.put(dealerGameResult, results.getOrDefault(dealerGameResult, 0) + 1);
                }
        );
        return results;
    }

    private static GameResult getOpposite(GameResult gameResult) {
        if (gameResult.equals(WIN)) {
            return LOSE;
        }

        if (gameResult.equals(LOSE)) {
            return WIN;
        }

        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
