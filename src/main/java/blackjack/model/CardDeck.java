package blackjack.model;

import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final Cards cards;

    public CardDeck(final Cards cards) {
        this.cards = cards;
    }

    public Cards draw(final int drawSize) {
        return new Cards(cards.pick(drawSize));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getValues());
    }

}
