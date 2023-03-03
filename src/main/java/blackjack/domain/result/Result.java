package blackjack.domain.result;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private static final int MAXIMUM_POINT = 21;

    private final Map<Player, Rank> results;

    private Result(Map<Player, Rank> results) {
        this.results = results;
    }

    public static Result from(Players players) {
        Map<Player, Rank> results = new LinkedHashMap<>();
        int dealerPoint = players.getDealer().getTotalPoint();
        List<Player> challengers = players.getChallengers();
        for (Player challenger : challengers) {
            Rank rank = makePlayerResult(dealerPoint, challenger);
            results.put(challenger, rank);
        }
        return new Result(results);
    }

    private static Rank makePlayerResult(int dealerPoint, Player challenger) {
        int challengerPoint = challenger.getTotalPoint();
        if (isOverMaximumPoint(dealerPoint)) {
            return getRankWhenDealerOverPoint(challengerPoint);
        }
        return getRankWhenDealerNotOverPoint(dealerPoint, challengerPoint);
    }

    private static Rank getRankWhenDealerOverPoint(int challengerPoint) {
        if (isOverMaximumPoint(challengerPoint)) {
            return Rank.DRAW;
        }
        return Rank.WIN;
    }

    private static Rank getRankWhenDealerNotOverPoint(int dealerPoint, int challengerPoint) {
        if (isOverMaximumPoint(challengerPoint)) {
            return Rank.LOSE;
        }
        return comparePoint(dealerPoint, challengerPoint);
    }

    private static Rank comparePoint(int dealerPoint, int challengerPoint) {
        if (challengerPoint < dealerPoint) {
            return Rank.LOSE;
        }
        if (challengerPoint > dealerPoint) {
            return Rank.WIN;
        }
        return Rank.DRAW;
    }

    private static boolean isOverMaximumPoint(int sum) {
        return sum > MAXIMUM_POINT;
    }

    public Rank getChallengerResult(Player player) {
        return results.get(player);
    }

    public Map<Rank, Integer> getDealerResult() {
        Map<Rank, Integer> dealerResult = new HashMap<>();
        for (Rank rank : results.values()) {
            Rank dealerRank = rank.getOpposite();
            dealerResult.put(dealerRank, dealerResult.getOrDefault(dealerRank, 0) + 1);
        }
        return dealerResult;
    }
}
