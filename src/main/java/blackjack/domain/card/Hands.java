package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Hands {

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }
}
