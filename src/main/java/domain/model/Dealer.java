package domain.model;

public class Dealer extends Participant {

    private static final int MIN_SCORE_THRESHOLD = 16;

    public Dealer(final Cards cards) {
        super(cards);
    }

    public boolean canReceiveCard() {
        return super.getScore().getValue() <= MIN_SCORE_THRESHOLD;
    }
}
