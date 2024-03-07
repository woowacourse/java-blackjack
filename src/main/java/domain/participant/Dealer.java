package domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int THRESHOLD_SCORE = 16;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return score() <= THRESHOLD_SCORE;
    }
}
