package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck createCardDeck() {
        List<Card> cards = shuffleNewDeck();
        return new CardDeck(cards);
    }

    private static List<Card> shuffleNewDeck() {
        List<Card> cards = Card.values();
        Collections.shuffle(cards);
        return cards;
    }

    public Card pickRandomCard() {
        try {
            return cards.removeFirst();
        } catch (NoSuchElementException e) {
            cards.addAll(shuffleNewDeck());
            return pickRandomCard();
        }
    }
}
