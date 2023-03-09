package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int DEALER_HIT_BASED_NUMBER = 16;
    public static final String DEALER_NAME = "딜러";


    public Dealer() {
        super(new ParticipantName(DEALER_NAME));
    }

    @Override
    public boolean decideHit() {
        return calculateCardNumber() <= DEALER_HIT_BASED_NUMBER;
    }
}
