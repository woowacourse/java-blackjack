package blackjack.domain;

public class Player extends Participant {


    private static final int BLACKJACK_MAX_NUMBER = 21;


    public Player(ParticipantName participantName) {
        super(participantName);
    }

    @Override
    boolean decideHit() {
        return calculateCardNumber() < BLACKJACK_MAX_NUMBER;
    }
}
