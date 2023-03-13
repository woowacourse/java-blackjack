package domain;

import java.util.Map;

public class Dealer extends Participant {

    private static final int SHOULD_DRAW_SCORE_UPPER_BOUND_EXCLUSIVE = 17;
    private static final String NAME = "딜러";

    public boolean shouldDrawCard() {
        return SHOULD_DRAW_SCORE_UPPER_BOUND_EXCLUSIVE > hand.calculateScore();
    }

    public Map<String, PlayerOutcome> battleWith(final Players players) {
        return players.computeWinLoss(hand);
    }

    @Override
    public String name() {
        return NAME;
    }
}
