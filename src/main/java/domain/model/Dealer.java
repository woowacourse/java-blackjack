package domain.model;

import domain.vo.Score;

public class Dealer extends Participant {

    private static final Score MIN_SCORE_THRESHOLD = Score.of(16);

    public Dealer(final Cards cards) {
        super(cards);
    }

    public static Dealer withEmptyCards() {
        return new Dealer(Cards.makeEmpty());
    }

    @Override
    public boolean canReceiveCard() {
        return super.getScore().lessThanOrEqualTo(MIN_SCORE_THRESHOLD);
    }
}
