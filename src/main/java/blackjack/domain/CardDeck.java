package blackjack.domain;

import java.util.HashSet;
import java.util.Set;

public class CardDeck {
    private final Set<Card> cards;

    public CardDeck(Set<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck createCardDeck() {
        Set<Card> cards = new HashSet(Card.values());
        return new CardDeck(cards);
    }

}
