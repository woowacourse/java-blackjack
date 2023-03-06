package blackjack.domain.result;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Collections;
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
        for (Player challenger : players.getChallengers()) {
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

    public int getDealerWinCount() {
        return Collections.frequency(results.values(), Rank.LOSE);
    }

    public int getDealerDrawCount() {
        return Collections.frequency(results.values(), Rank.DRAW);
    }

    public int getDealerLoseCount() {
        return Collections.frequency(results.values(), Rank.WIN);
    }
}
