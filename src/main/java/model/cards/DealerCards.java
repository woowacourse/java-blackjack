package model.cards;

import java.util.List;
import model.card.Card;

final public class DealerCards extends Cards{

    private static final int INITIAL_CARDS_COUNT = 2;

    public DealerCards(final List<Card> cards) {
        super(cards);
    }

    public Card getFirstCard() {
        return super.cards.getFirst();
    }

    public int getAdditionalDrawCount() {
        return cards.size() - INITIAL_CARDS_COUNT;
    }

}
