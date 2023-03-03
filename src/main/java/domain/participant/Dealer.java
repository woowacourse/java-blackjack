package domain.participant;

public class Dealer extends Participant {

    private static final int STAY_LOWER_BOUND = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isStand() {
        return calculateScore() > STAY_LOWER_BOUND;
    }

    @Override
    public void stand() {
        throw new UnsupportedOperationException();
    }
}
