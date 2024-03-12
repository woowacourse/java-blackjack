package domain.participant;

public class Dealer extends Participant {

    public static final int THRESHOLD_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return score() <= THRESHOLD_SCORE;
    }
}
