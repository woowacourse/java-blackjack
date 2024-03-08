package domain.deck;

import domain.card.Card;

public class DealerDeck extends UserDeck {
    private final static int ADD_CONDITION = 16;
    private static final int FIRST_INDEX = 0;

    public Card getVisibleCard() {
        return cards.get(FIRST_INDEX);
    }

    public boolean isAddCondition() {
        return sumCard() <= ADD_CONDITION;
    }
}
