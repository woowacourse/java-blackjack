package domain.game;

import domain.card.CardBundle;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public Result judge(CardBundle playerBundle, CardBundle dealerBundle) {
        if (playerBundle.isBlackjack() && dealerBundle.isBlackjack()) {
            return Result.TIE;
        }
        if (playerBundle.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (playerBundle.isBust()) {
            return Result.LOSE;
        }
        if (dealerBundle.isBust()) {
            return Result.WIN;
        }
        if (playerBundle.calculateScore() > dealerBundle.calculateScore()) {
            return Result.WIN;
        }
        if (playerBundle.calculateScore() == dealerBundle.calculateScore()) {
            return Result.TIE;
        }
        return Result.LOSE;
    }

    public Map<Result, Integer> countDealerResult(Map<?, Result> playerResults) {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>();
        dealerResult.put(Result.WIN,
                countByResult(playerResults, Result.LOSE));
        dealerResult.put(Result.LOSE,
                countByResult(playerResults, Result.WIN) + countByResult(playerResults, Result.BLACKJACK_WIN));
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
