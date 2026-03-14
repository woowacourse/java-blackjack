package domain.game;

import domain.participant.Participant;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public Result judge(Participant player, Participant dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Result.TIE;
        }
        if (player.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return Result.LOSE;
        }
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        if (player.getScore() > dealer.getScore()) {
            return Result.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
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
