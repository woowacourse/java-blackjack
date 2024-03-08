package domain.deck;

import domain.card.Card;

public class DealerDeck extends UserDeck {
    private static final int FIRST_INDEX = 0;
    private static final int DEALER_CARD_CONDITION = 16;


    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }

    public boolean isAdd() {
        return sumCard() <= DEALER_CARD_CONDITION;
    }
}
