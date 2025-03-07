package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> cards;

    private CardDeck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck of(List<Card> cards) {
        Stack<Card> deck = new Stack<>();
        deck.addAll(cards);
        return new CardDeck(deck);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card popCard() {
        return cards.pop();
    }
}
