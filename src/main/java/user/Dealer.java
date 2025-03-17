package user;

import card.Card;
import java.util.List;

public class Dealer extends User {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_MAX_SCORE = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean isDrawable() {
        return !cardHand.isAtLeastScore(DEALER_MAX_SCORE);
    }

    @Override
    public List<Card> openInitialCard() {
        return this.cardHand.getFirstCard();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
