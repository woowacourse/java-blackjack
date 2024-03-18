package domain.participant;

public class Dealer extends Participant {
    private static final String DEFAULT_NAME = "딜러";
    private static final int DEALER_MIN_SCORE_POLICY = 17;

    public Dealer() {
        super(DEFAULT_NAME);
    }

    @Override
    public boolean isNotFinished() {
        return calculateScore() < DEALER_MIN_SCORE_POLICY;
    }
}
