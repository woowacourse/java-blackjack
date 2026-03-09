package model;

import static constant.GameRule.DEALER_NAME;

public class Dealer extends Participant{
    private static final Integer CARD_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public boolean canDraw() {
        return !super.isMoreThanScore(CARD_DRAW_THRESHOLD);
    }
}
