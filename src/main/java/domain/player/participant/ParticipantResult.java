package domain.player.participant;

import domain.player.dealer.Dealer;
import domain.player.dealer.DealerResult;

public enum ParticipantResult {
    WINNER,
    LOSER,
    DRAWER;

    public static ParticipantResult matchBetween(final Participant participant, final Dealer dealer) {
        if (participant.isBurst()) {
            return ParticipantResult.LOSER;
        }
        if (dealer.isBurst()) {
            return ParticipantResult.WINNER;
        }
        if (participant.score() > dealer.score()) {
            return ParticipantResult.WINNER;
        }
        if (participant.score() == dealer.score()) {
            return ParticipantResult.DRAWER;
        }
        return ParticipantResult.LOSER;
    }

    public DealerResult convertToDealerResult() {
        if (this == WINNER) {
            return DealerResult.LOSER;
        }
        if (this == LOSER) {
            return DealerResult.WINNER;
        }
        return DealerResult.DRAWER;
    }
}
