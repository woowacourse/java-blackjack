package domain;

import domain.constants.ProfitRate;
import java.util.ArrayList;
import java.util.List;

public class GameRule {
    public static final int TWO_CARDS_IN_HAND = 2;

    private final Dealer dealer;
    private final Gamers gamers;

    public GameRule(final Dealer dealer, final Gamers gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public List<ProfitRate> judge() {
        if (dealer.isBusted()) {
            return judgeGamersIfDealerBusted();
        }

        List<ProfitRate> gameResult = new ArrayList<>();
        for (Gamer gamer : gamers.listOf()) {
            checkWinner(gamer, gameResult);
        }

        return gameResult;
    }

    public List<ProfitRate> judgeGamersIfDealerBusted() {
        List<ProfitRate> gameResult = new ArrayList<>();
        for (Gamer gamer : gamers.listOf()) {
            gameResult.add(getProfitRateWhenDealerBusted(gamer));
        }

        return gameResult;
    }

    private ProfitRate getProfitRateWhenDealerBusted(final Gamer gamer) {
        if (gamer.isBusted()) {
            return ProfitRate.LOSE;
        }

        if (gamer.isBlackjack()) {
            return getProfitRateByBlackJackCardCount(gamer);
        }

        return ProfitRate.WIN;
    }

    private ProfitRate getProfitRateByBlackJackCardCount(final Gamer gamer) {
        if (gamer.getHandSize() == TWO_CARDS_IN_HAND) {
            return ProfitRate.BLACKJACK;
        }
        return ProfitRate.WIN;
    }

    private void checkWinner(final Gamer gamer, final List<ProfitRate> gameResult) {
        if (gamer.isBusted()) {
            gameResult.add(ProfitRate.LOSE);
            return;
        }

        if (gamer.hasSameScoreAs(dealer)) {
            gameResult.add(getProfitRateByCardCount(gamer));
            return;
        }

        if (gamer.isBlackjack()) {
            gameResult.add(getProfitRateByBlackJackCardCount(gamer));
            return;
        }

        if (gamer.hasMoreScoreThan(dealer)) {
            gameResult.add(ProfitRate.WIN);
            return;
        }

        gameResult.add(ProfitRate.LOSE);
    }

    private ProfitRate getProfitRateByCardCount(final Gamer gamer) {
        if (gamer.hasLessCardThan(dealer)) {
            return ProfitRate.WIN;
        }

        if (gamer.hasMoreCardThan(dealer)) {
            return ProfitRate.LOSE;
        }

        return ProfitRate.PUSH;
    }
}
