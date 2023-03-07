package blackjack.domain.participant;

public class Player extends Participant {

    private static final int BLACKJACK_MAX_NUMBER = 21;

    public Player(ParticipantName participantName) {
        super(participantName);
    }

    @Override
    public boolean decideHit() {
        if (calculateCardNumber() < BLACKJACK_MAX_NUMBER) {
            return true;
        }
        return false;
    }
}
