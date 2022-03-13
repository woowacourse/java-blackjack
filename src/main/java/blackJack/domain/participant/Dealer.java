package blackJack.domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MAXIMUM_RECEIVE_CARD_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return this.getScore() <= DEALER_MAXIMUM_RECEIVE_CARD_SCORE;
    }
}
