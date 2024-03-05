package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck from(List<Card> cards) {
        return new Deck(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }

}
