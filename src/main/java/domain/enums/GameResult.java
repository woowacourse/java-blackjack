package domain.enums;

import constant.GameRule;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GameResult {

    BLACKJACK_WIN("승"),
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public static GameResult calculatePlayerResult(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        boolean dealerBust = dealer.isBust();

        if (player.isBust() || (playerScore < dealerScore && !dealerBust)) {
            return GameResult.LOSE;
        }
        if (playerScore == dealerScore) {
            return GameResult.DRAW;
        }
        if (player.getHand().size() == 2 && playerScore == GameRule.BLACKJACK_CRITERION) {
            return GameResult.BLACKJACK_WIN;
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
        if (gameResult.equals(WIN) || gameResult.equals(BLACKJACK_WIN)) {
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
