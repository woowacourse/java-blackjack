package domain.enums;

import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result calculatePlayerResult(Player player, int dealerScore, boolean dealerBurst) {
        int playerScore = player.getScore();

        if (player.isBust() || (playerScore < dealerScore && !dealerBurst)) {
            return Result.LOSE;
        }
        if (playerScore == dealerScore) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    public static Map<Result, Integer> calculateDealerResult(List<Result> playerResults) {
        Map<Result, Integer> results = new HashMap<>();
        playerResults.forEach(
                result -> {
                    Result dealerResult = Result.getOpposite(result);
                    results.put(dealerResult, results.getOrDefault(dealerResult, 0) + 1);
                }
        );
        return results;
    }

    private static Result getOpposite(Result result) {
        if (result.equals(WIN)) {
            return LOSE;
        }

        if (result.equals(LOSE)) {
            return WIN;
        }

        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
