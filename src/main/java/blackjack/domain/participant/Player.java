package blackjack.domain.participant;

import blackjack.domain.card.Hand;

public class Player extends Participant {

    private static final int BLACKJACK_MAX_NUMBER = 21;

    public Player(ParticipantName participantName, Hand hand) {
        super(participantName, hand);
    }

    @Override
    public boolean decideHit() {
        if (calculateCardNumber() < BLACKJACK_MAX_NUMBER) {
            return true;
        }
        return false;
    }
}
