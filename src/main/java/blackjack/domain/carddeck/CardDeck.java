package blackjack.domain.carddeck;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private final Deque<Card> deck;

    private CardDeck(final Deque<Card> cards) {
        this.deck = new ArrayDeque<>(cards);
    }

    public static CardDeck newShuffledDeck() {
        List<Card> cards = CardDeckGenerator.generate();
        Collections.shuffle(cards);
        return new CardDeck(new ArrayDeque<>(cards));
    }

    public Card draw() {
        return this.deck.pop();
    }
}
