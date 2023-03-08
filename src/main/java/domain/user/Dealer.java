package domain.user;

import domain.card.Card;
import domain.card.Score;

public class Dealer extends User {

    private static final Score UNDER_OVER_SCORE = new Score(17);
    private static final int VISIBLE_CARD_INDEX = 0;

    @Override
    public boolean isHittable() {
        return cards.isUnder(UNDER_OVER_SCORE);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    public Card getVisibleCard() {
        return this.getCards().get(VISIBLE_CARD_INDEX);
    }
}
