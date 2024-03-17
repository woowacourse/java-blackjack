package domain.game;

import static domain.participant.Participants.CACHED_DEALER;

import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Player;

public final class GameRule {
    public static Outcome judgeWhenDealerIsBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }

    public static Outcome judgeWhenDealerIsBusted(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.BLACKJACK;
        }
        if (player.isNotBusted()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }


    public static Outcome judgeWhenDealerIsNotBustedAndNotBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.BLACKJACK;
        }
        if (player.isBusted()) {
            return Outcome.LOSE;
        }
        if (player.isNotSameScoreAs(CACHED_DEALER)) {
            return verifyHasMoreScoreThanDealer(player, CACHED_DEALER);
        }
        return verifyHasLessOrSameCardThanDealer(player, CACHED_DEALER);
    }

    private static Outcome verifyHasMoreScoreThanDealer(final Player player, final Dealer dealer) {
        if (player.hasMoreScoreThan(dealer)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private static Outcome verifyHasLessOrSameCardThanDealer(
            final Player player,
            final Dealer dealer
    ) {
        if (player.hasLessOrSameCardThan(dealer)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}
