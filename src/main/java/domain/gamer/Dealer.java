package domain.gamer;

import domain.card.possessable.HandCards;
import domain.score.BlackJackScoreManager;

public class Dealer extends Gamer {
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return BlackJackScoreManager.DEALER_DRAW_THRESHOLD.isLargerOrEqualThan(calculateScore());
    }
}
