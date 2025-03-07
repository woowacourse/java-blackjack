package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;

public enum ParticipantResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String description;

    ParticipantResult(final String description) {
        this.description = description;
    }

    public static ParticipantResult of(final Dealer dealer, final Participant participant) {
        if (participant.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareDealerAndParticipantPoint(dealer, participant);
    }

    private static ParticipantResult compareDealerAndParticipantPoint(final Dealer dealer, final Participant participant) {
        int dealerPoint = dealer.calculatePoint();
        int participantPoint = participant.calculatePoint();
        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        if (dealerPoint < participantPoint) {
            return WIN;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
