package domain.model;

public class Dealer extends Player {

    private static final int MIN_SCORE_THRESHOLD = 16;
    private static final String DEALER_DEFAULT_NAME = "딜러";

    public Dealer(final Cards cards) {
        super(cards, DEALER_DEFAULT_NAME);
    }

    public boolean canReceiveCard() {
        return super.getScore().getValue() <= MIN_SCORE_THRESHOLD;
    }
}
