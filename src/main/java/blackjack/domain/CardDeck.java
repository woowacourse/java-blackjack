package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck createCardDeck() {
        return new CardDeck(Card.values());
    }

    public Card pickRandomCard() {
        Collections.shuffle(cards);
        return cards.removeFirst();
    }
}
