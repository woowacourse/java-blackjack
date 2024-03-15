package domain.participant;

import domain.card.Score;

public class Dealer extends Participant {
    private static final String DEFAULT_NAME = "딜러";
    private static final int DEALER_MIN_SCORE_POLICY = 17;

    public Dealer() {
        super(DEFAULT_NAME);
    }

    public boolean isNotExceedDrawPolicy() {
        return calculateScore().isLessThan(Score.get(DEALER_MIN_SCORE_POLICY));
    }
}
