package blackjack.domain.participant;

public class Player extends Participant {

    private Amount amount;

    private static final int BLACKJACK_MAX_NUMBER = 21;


    public Player(final ParticipantName participantName,final Amount amount) {
        super(participantName);
        this.amount = amount;
    }

    @Override
    public boolean decideHit() {
        return calculateCardNumber() < BLACKJACK_MAX_NUMBER;
    }
}
