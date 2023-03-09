package domain.user;

import domain.Card;

public class Dealer extends User {

    private static final int UNDER_OVER_SCORE = 17;

    @Override
    public boolean isHittable() {
        return cards.isUnder(UNDER_OVER_SCORE);
    }

    public Card getCardWithHidden() {
        return cards.getFirstCard();
    }
}
