package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public class FixedDeck implements Deck {

    private final List<Card> cards;
    private int index = 0;

    public FixedDeck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public Card draw() {
        return cards.get(index++);
    }
}
