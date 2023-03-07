package blackjack.domain.result;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.Score;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, Rank> results;

    private Result(final Map<Player, Rank> results) {
        this.results = results;
    }

    public static Result from(final Players players) {
        Map<Player, Rank> results = new LinkedHashMap<>();
        Score dealerPoint = players.getDealer().getTotalPoint();
        for (Player challenger : players.getChallengers()) {
            Rank rank = makePlayerResult(dealerPoint, challenger);
            results.put(challenger, rank);
        }
        return new Result(results);
    }

    private static Rank makePlayerResult(final Score dealerPoint, final Player challenger) {
        Score challengerPoint = challenger.getTotalPoint();
        if (dealerPoint.isBust()) {
            return getRankWhenDealerOverPoint(challengerPoint);
        }
        return getRankWhenDealerNotOverPoint(dealerPoint, challengerPoint);
    }

    private static Rank getRankWhenDealerOverPoint(final Score challengerPoint) {
        if (challengerPoint.isBust()) {
            return Rank.DRAW;
        }
        return Rank.WIN;
    }

    private static Rank getRankWhenDealerNotOverPoint(final Score dealerPoint, final Score challengerPoint) {
        if (challengerPoint.isBust()) {
            return Rank.LOSE;
        }
        return comparePoint(dealerPoint, challengerPoint);
    }

    private static Rank comparePoint(final Score dealerPoint, final Score challengerPoint) {
        if (dealerPoint.isBiggerThan(challengerPoint)) {
            return Rank.LOSE;
        }
        if (challengerPoint.isBiggerThan(dealerPoint)) {
            return Rank.WIN;
        }
        return Rank.DRAW;
    }

    public Rank getChallengerResult(final Player player) {
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
