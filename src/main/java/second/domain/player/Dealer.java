package second.domain.player;

import second.domain.card.HandCards;
import second.domain.card.Score;

public class Dealer extends Player {
    private static final Score DEALER_DRAW_THRESHOLD = new Score(16);

    private static final String DEALER_NAME = "딜러";

    @Override
    public boolean canDrawMore() {
        return isSmallerOrEqualThan(DEALER_DRAW_THRESHOLD);
    }

    public Dealer() {
        super(DEALER_NAME, new HandCards());
    }
}
