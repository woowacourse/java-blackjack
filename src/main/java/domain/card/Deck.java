package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private static final int INIT_CARDS_SIZE = 2;

    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        Stack<Card> cards = new Stack<>();
        for (Type type : Type.values()) {
            cards.addAll(createBySymbol(type));
        }
        return new Deck(cards);
    }

    private static Stack<Card> createBySymbol(Type type) {
        Stack<Card> cards = new Stack<>();
        for (Symbol symbol : Symbol.values()) {
            cards.add(new Card(symbol, type));
        }
        return cards;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card handOutCard() {
        shuffle();
        return cards.pop();
    }

    boolean isEmpty() {
        return cards.isEmpty();
    }

    public PlayingCards getInitCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARDS_SIZE; i++) {
            cards.add(handOutCard());
        }
        return new PlayingCards(cards);
    }


}
