package first.domain.gamer;

import first.domain.card.possessable.HandCards;

import static first.domain.score.ScoreManagable.DEALER_DRAW_THRESHOLD;

public class Dealer extends Gamer {
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return DEALER_DRAW_THRESHOLD.isLargerOrEqualThan(calculateScore());
    }
}
