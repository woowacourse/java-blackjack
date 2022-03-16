package blackjack.domain.game;

import blackjack.domain.participant.Participant;

public class BettingResult {

    private final Participant participant;
    private final int value;

    public BettingResult(Participant participant, int value) {
        this.participant = participant;
        this.value = value;
    }

    public Participant getParticipant() {
        return participant;
    }

    public int getValue() {
        return value;
    }
}
