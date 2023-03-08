package domain.model;

import domain.vo.Score;

public class Dealer extends Participant {

    private static final int MIN_SCORE_THRESHOLD = 16;

    public Dealer() {
        this(Cards.makeEmptyCards());
    }

    public Dealer(final Cards cards) {
        super(cards);
    }

    public boolean canReceiveCard() {
        return super.getScore().isLessThan(Score.of(MIN_SCORE_THRESHOLD));
    }
}
