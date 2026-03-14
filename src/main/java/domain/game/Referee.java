package domain.game;

import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {
    private static final int BUST_THRESHOLD = 21;

    public Result judge(int playerScore, int dealerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return Result.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return Result.WIN;
        }
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore == dealerScore) {
            return Result.TIE;
        }
        return Result.LOSE;
    }

    public Map<Result, Integer> countDealerResult(Map<?, Result> playerResults) {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>();
        dealerResult.put(Result.WIN, countByResult(playerResults, Result.LOSE));
        dealerResult.put(Result.LOSE, countByResult(playerResults, Result.WIN));
        dealerResult.put(Result.TIE, countByResult(playerResults, Result.TIE));
        return dealerResult;
    }

    private int countByResult(Map<?, Result> results, Result target) {
        int count = 0;
        for (Result result : results.values()) {
            if (result == target) {
                count++;
            }
        }
        return count;
    }
}
