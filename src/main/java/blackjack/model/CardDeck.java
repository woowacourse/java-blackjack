package blackjack.model;

import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final Cards cards;

    public CardDeck(final Cards cards) {
        this.cards = cards;
    }

    public List<Card> draw(final int drawSize) {
        return cards.pick(drawSize);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getValues());
    }

}
