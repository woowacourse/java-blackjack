package domain.game;

import domain.card.Score;
import domain.user.Dealer;
import domain.user.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    public static Map<String, Result> judgeTotalPlayerResult(final List<Player> players, final Dealer dealer) {
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (Player player : players) {
            playerResults.put(player.getName(), judgePlayerResult(player.getScore(), dealer.getScore()));
        }
        return playerResults;
    }

    public static Result judgePlayerResult(final Score playerScore, final Score dealerScore) {
        if (playerScore.equals(dealerScore) || (playerScore.isBust() && dealerScore.isBust())) {
            return Result.PUSH;
        }
        if ((!dealerScore.isBust() && playerScore.isLessThan(dealerScore)) || playerScore.isBust()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    public static Map<Result, Integer> judgeTotalDealerResult(final Map<String, Result> playerResults) {
        Map<Result, Integer> totalDealerResult = new HashMap<>();
        playerResults.values().forEach(playerResult -> calculateResultCountOfDealer(totalDealerResult, playerResult));
        return totalDealerResult;
    }

    private static void calculateResultCountOfDealer(final Map<Result, Integer> totalDealerResult,
                                                     final Result playerResult) {
        Result dealerResult = playerResult.getResultOfOpponent();
        int dealerResultCount = increaseResultCount(totalDealerResult, dealerResult);
        totalDealerResult.put(dealerResult, dealerResultCount);
    }

    private static int increaseResultCount(final Map<Result, Integer> totalDealerResult, Result dealerResult) {
        int originalCount = totalDealerResult.getOrDefault(dealerResult, 0);
        return originalCount + 1;
    }
}
