package second.domain.player;

import second.domain.card.HandCards;
import second.domain.score.Score;

public class Dealer extends Gamer {
    private static final Score DEALER_DRAW_THRESHOLD = new Score(16);
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return !isLargerScoreThan(DEALER_DRAW_THRESHOLD);
    }
}
