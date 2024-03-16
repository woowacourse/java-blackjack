package domain.game;

import static domain.participant.Participants.CACHED_DEALER;

import controller.dto.response.PlayerOutcome;
import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    public List<PlayerOutcome> judge() {
        if (CACHED_DEALER.isBlackJack()) {
            return participants.getPlayersOutcomeIf(
                    this::judgeWhenDealerIsBlackJack
            );
        }

        if (CACHED_DEALER.isBusted()) {
            return participants.getPlayersOutcomeIf(
                    this::judgeWhenDealerIsBusted
            );
        }

        return participants.getPlayersOutcomeIf(
                this::judgeWhenDealerIsNotBustedAndNotBlackJack
        );
    }

    private Outcome judgeWhenDealerIsBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }

    private Outcome judgeWhenDealerIsBusted(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.BLACKJACK;
        }
        if (player.isNotBusted()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private Outcome judgeWhenDealerIsNotBustedAndNotBlackJack(final Player player) {
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

    private Outcome verifyHasMoreScoreThanDealer(final Player player, final Dealer dealer) {
        if (player.hasMoreScoreThan(dealer)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private Outcome verifyHasLessOrSameCardThanDealer(final Player player, final Dealer dealer) {
        if (player.hasLessOrSameCardThan(dealer)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}
