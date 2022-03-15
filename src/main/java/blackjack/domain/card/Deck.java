package blackjack.domain.card;

import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    private Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(CardGeneratingStrategy strategy) {
        return new Deck(strategy.generate());
    }

    public static Deck createBy(final List<Card> cards) {
        final Stack<Card> deck = new Stack<>();
        deck.addAll(cards);

        return new Deck(deck);
    }

    public Card drawCard() {
        return deck.pop();
    }
}
