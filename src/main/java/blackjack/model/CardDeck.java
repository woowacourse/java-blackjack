package blackjack.model;

import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final Cards cards;

    public CardDeck(final Cards cards) {
        this.cards = cards;
    }

    public List<Card> draw(final int drawSize) {
        List<Card> values = cards.getValues();
        Collections.shuffle(values);
        List<Card> result = List.copyOf(values.subList(0, drawSize));
        values.removeAll(result);
        return result;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getValues());
    }

}
