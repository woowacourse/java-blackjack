package domain.game;

import static domain.constants.Outcome.BLACKJACK;
import static domain.constants.Outcome.LOSE;
import static domain.constants.Outcome.TIE;
import static domain.constants.Outcome.WIN;

import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Player;

public final class GameRule {
    public static Outcome judgeWhenDealerIsBlackJack(final Player player, final Dealer dealer) {
        if (player.isBlackJack()) {
            return TIE;
        }
        return LOSE;
    }

    public static Outcome judgeWhenDealerIsBusted(final Player player, final Dealer dealer) {
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        if (player.isNotBusted()) {
            return WIN;
        }
        return LOSE;
    }


    public static Outcome judgeWhenDealerIsNormal(final Player player, final Dealer dealer) {
        if (player.isBlackJack()) {
            return BLACKJACK;
        }
        if (player.isBusted()) {
            return LOSE;
        }
        if (player.isNotSameScoreAs(dealer)) {
            return verifyHasMoreScoreThanDealer(player, dealer);
        }
        return verifyHasLessOrSameCardThanDealer(player, dealer);
    }

    private static Outcome verifyHasMoreScoreThanDealer(final Player player, final Dealer dealer) {
        if (player.hasMoreScoreThan(dealer)) {
            return WIN;
        }
        return LOSE;
    }

    private static Outcome verifyHasLessOrSameCardThanDealer(
            final Player player,
            final Dealer dealer
    ) {
        if (player.hasLessOrSameCardThan(dealer)) {
            return WIN;
        }
        return LOSE;
    }
}
