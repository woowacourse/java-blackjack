package blackjack.domain.result;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Player, Money> results;

    private Result(final Map<Player, Money> results) {
        this.results = results;
    }

    public static Result from(final Players players) {
        Map<Player, Money> results = new LinkedHashMap<>();
        Player dealer = players.getDealer();
        for (Challenger challenger : players.getChallengers()) {
            Rank rank = makePlayerResult(dealer, challenger);
            Money profit = calculateProfit(challenger, rank);
            results.put(challenger, profit);
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
        return getRankWhenChallengerWin(challenger);
    }

    private static Rank getRankWhenChallengerWin(final Player challenger) {
        if (challenger.isBlackjack()) {
            return Rank.BLACKJACK;
        }
        return Rank.WIN;
    }

    private static Rank getRankWhenBothNotBust(final Player dealer, final Player challenger) {
        if (challenger.moreScoreThan(dealer)) {
            return getRankWhenChallengerWin(challenger);
        }
        return Rank.LOSE;
    }

    private static Money calculateProfit(Challenger challenger, Rank rank) {
        Money bettingMoney = challenger.getMoney();
        return Money.multiply(bettingMoney, rank.getRateOfReturn());
    }

    public Money getChallengerProfit(final Player player) {
        return results.get(player);
    }

    public Money getDealerProfit() {
        Money challengersProfit = results.values()
                .stream()
                .reduce(Money.zero(), Money::sum);
        return Money.multiply(challengersProfit, -1);
    }
}
