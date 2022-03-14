package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    private Deck(Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck createBy(final List<Card> cards) {
        final Stack<Card> deck = new Stack<>();
        deck.addAll(cards);

        return new Deck(deck);
    }

    public static Deck create() {
        final List<Card> list = Card.getAllCards();
        Collections.shuffle(list);

        final Stack<Card> deck = new Stack<>();
        deck.addAll(list);

        return new Deck(deck);
    }

    public Card drawCard() {
        return deck.pop();
    }
}
