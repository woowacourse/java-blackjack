package blackjack.domain.result;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Player, Rank> results;

    private Result(final Map<Player, Rank> results) {
        this.results = results;
    }

    public static Result from(final Players players) {
        Map<Player, Rank> results = new LinkedHashMap<>();
        Player dealer = players.getDealer();
        for (Player challenger : players.getChallengers()) {
            Rank rank = makePlayerResult(dealer, challenger);
            results.put(challenger, rank);
        }
        return new Result(results);
    }

    private static Rank makePlayerResult(final Player dealer, final Player challenger) {
        if (challenger.isSameScore(dealer)) {
            return Rank.DRAW;
        }
        return getRankWhenScoresNotSame(dealer, challenger);
    }

    private static Rank getRankWhenScoresNotSame(final Player dealer, final Player challenger) {
        if (dealer.isBust() || challenger.isBust()) {
            return getRankWhenSomebodyBust(dealer, challenger);
        }
        return getRankWhenBothNotBust(dealer, challenger);
    }

    private static Rank getRankWhenSomebodyBust(final Player dealer, final Player challenger) {
        if (dealer.isBust()) {
            return getRankWhenDealerBust(challenger);
        }
        return Rank.LOSE;
    }

    private static Rank getRankWhenDealerBust(final Player challenger) {
        if (challenger.isBust()) {
            return Rank.DRAW;
        }
        return Rank.WIN;
    }

    private static Rank getRankWhenBothNotBust(final Player dealer, final Player challenger) {
        if (challenger.moreScoreThan(dealer)) {
            return Rank.WIN;
        }
        return Rank.LOSE;
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
